package com.mishchenkov.server.request;

import java.net.Socket;

public interface RequestHandlerFactory {

    RequestHandler createHandler(Socket socket);

}
