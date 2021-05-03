package com.mishchenkov.server;

import com.mishchenkov.server.request.RequestHandlerFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class AbstractServer implements Server {

    private final Logger logger = Logger.getLogger(AbstractServer.class);

    protected final int port;
    protected final RequestHandlerFactory handlerFactory;
    protected final Thread thread;

    protected AbstractServer(int port, RequestHandlerFactory handlerFactory) {
        this.port = port;
        this.handlerFactory = handlerFactory;
        thread = new Thread(new ServerRunnable());
    }

    private class ServerRunnable implements Runnable {

        @Override
        public void run() {

            try (ServerSocket serverSocket = new ServerSocket(port)) {

                Executor pool = Executors.newFixedThreadPool(5);

                while (!thread.isInterrupted()) {
                    Socket socket = serverSocket.accept();

                    pool.execute(handlerFactory.createHandler(socket));
                }

            } catch (IOException e) {
               logger.warn(e.getMessage(), e);
            }

        }
    }
}
