package com.mishchenkov.client;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;
import static com.mishchenkov.constant.Constants.SERVER_HOST;
import static com.mishchenkov.constant.Constants.TCP_SERVER_PORT;

public class TCPClient {

    private static final Logger LOGGER = Logger.getLogger(TCPClient.class);

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        String request;
        String response;

        while (true) {

            try (Socket socket = new Socket(SERVER_HOST, TCP_SERVER_PORT) ) {

                try (
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
                ) {
                    request = getConsoleCommand();
                    if ("exit".equals(request)) break;

                    outputStream.writeUTF(request);
                    outputStream.flush();

                    response = inputStream.readUTF();
                    LOGGER.info(String.format("Server response: [%s].%s", response, LINE_SEPARATOR));
                }

            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
                break;
            }

        }

    }

    private static String getConsoleCommand() {
        LOGGER.info("enter request: ");
        return SCANNER.nextLine();
    }
}
