package com.mishchenkov.server.request;

import com.mishchenkov.server.adapter.ResponseAdapter;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_NOT_GET_ITEM_OR_COUNT;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_TCP_GET_COUNT;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_TCP_GET_ITEM;

public class TCPRequestHandler implements RequestHandler {

    private final Logger logger = Logger.getLogger(TCPRequestHandler.class);

    private final Socket socket;
    private final Map<String, Function<String, String>> responseMap;

    public TCPRequestHandler(ResponseAdapter adapter, Socket socket) {
        this.socket = socket;

        responseMap = new TreeMap<>();
        responseMap.put(REGEXP_MATCH_TCP_GET_COUNT, s -> adapter.getCount());
        responseMap.put(REGEXP_MATCH_TCP_GET_ITEM, s -> adapter.getItem(s.replace("get item=", "")));
        responseMap.put(REGEXP_MATCH_NOT_GET_ITEM_OR_COUNT, s -> String.format("Error! [%s]", s));
    }

    @Override
    public void run() {
        logger.trace(String.format("# TCP Handler run %s", LINE_SEPARATOR));
        try {

            try (
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
            ) {

                String line = inputStream.readUTF();

                String response = responseMap.keySet().stream()
                        .filter(line::matches)
                        .findFirst()
                        .map(responseMap::get)
                        .orElseGet(() -> s -> "Error")
                        .apply(line);

                outputStream.writeUTF(response);
                outputStream.flush();

            } catch (IOException e) {
                logger.warn("# TCP Handler - connection was lost");
            }

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }
}
