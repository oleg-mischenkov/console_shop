package com.mishchenkov.server.request;

import com.mishchenkov.server.adapter.ResponseAdapter;

import java.net.Socket;

public class HTTPHandlerFactory implements RequestHandlerFactory {

    private final ResponseAdapter adapter;

    public HTTPHandlerFactory(ResponseAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RequestHandler createHandler(Socket socket) {
        return new HTTPRequestHandler(adapter, socket);
    }
}
