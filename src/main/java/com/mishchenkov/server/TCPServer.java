package com.mishchenkov.server;

import com.mishchenkov.server.request.RequestHandlerFactory;
import org.apache.log4j.Logger;

import static com.mishchenkov.constant.Constants.LINE_SEPARATOR;

public class TCPServer extends AbstractServer {

    private final Logger logger = Logger.getLogger(TCPServer.class);

    public TCPServer(int port, RequestHandlerFactory handlerFactory) {
        super(port, handlerFactory);
    }

    @Override
    public void startServer() {
        logger.trace(String.format("# Start TCP Server port: %d %s", port, LINE_SEPARATOR));
        thread.setDaemon(true);
        thread.start();

    }

    @Override
    public void stopServer() {
        if (!thread.isInterrupted()) {
            thread.interrupt();
        }
        logger.trace(String.format("# Stop TCP Server %s", LINE_SEPARATOR));
    }

}
