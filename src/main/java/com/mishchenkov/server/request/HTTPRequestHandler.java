package com.mishchenkov.server.request;

import com.mishchenkov.server.adapter.ResponseAdapter;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_GET_SHOP_COUNT;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_GET_SHOP_ITEM;
import static com.mishchenkov.constant.Constants.REGEXP_MATCH_GET_SHOP_REQUEST;

public class HTTPRequestHandler implements RequestHandler {

    private final Logger logger = Logger.getLogger(HTTPRequestHandler.class);

    private final Socket socket;
    private final Map<String, Function<String, String>> responseMap;

    public HTTPRequestHandler(ResponseAdapter adapter, Socket socket) {
        this.socket = socket;

        responseMap = new TreeMap<>();
        responseMap.put(REGEXP_MATCH_GET_SHOP_COUNT, s -> adapter.getCount());
        responseMap.put(REGEXP_MATCH_GET_SHOP_ITEM, s -> {
            String item = Arrays.stream(s.split(" "))
                    .filter(s1 -> s1.contains("?get_info="))
                    .limit(1)
                    .map(s1 -> s1.substring(s1.indexOf("get_info=") + "get_info=".length()))
                    .collect(Collectors.joining());
            return adapter.getItem(item);
        });
    }

    @Override
    public void run() {
        logger.trace(String.format("%s# HTTP Handler run [Thread: %s] %s",
                LINE_SEPARATOR, Thread.currentThread().getName(), LINE_SEPARATOR));
        try {

            try (
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream()
            ) {

                String header = readHeader(inputStream).split(LINE_SEPARATOR)[0];

                if (header.matches(REGEXP_MATCH_GET_SHOP_REQUEST)) {
                    String response = responseMap.keySet()
                            .stream()
                            .filter(header::matches)
                            .findFirst()
                            .map(responseMap::get)
                            .orElse(s -> "Error")
                            .apply(header);

                    sendResponse(response, outputStream);
                } else {
                    sendResponse("Unsupported URL", outputStream);
                }


            } catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }
        }

        logger.trace(String.format("# HTTP Handler FINISH [Thread: %s] %s",Thread.currentThread().getName(), LINE_SEPARATOR));
    }

    private void sendResponse(String msg, OutputStream outputStream) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: Apache" +
                "Date: "+ new Date().toString() +"\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "Content-Length: " + msg.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                "Connection: close\r\n\r\n";
        response += msg;

        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    private String readHeader(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();

        String ln;
        while (true) {
            ln = reader.readLine();
            if (ln == null || ln.isEmpty()) {
                break;
            }
            builder.append(ln.concat(LINE_SEPARATOR));
        }

        return builder.toString();
    }

}
