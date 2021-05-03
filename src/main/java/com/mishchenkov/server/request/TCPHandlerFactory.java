package com.mishchenkov.server.request;

import com.mishchenkov.server.adapter.ResponseAdapter;

import java.net.Socket;

public class TCPHandlerFactory implements RequestHandlerFactory {

    private final ResponseAdapter adapter;

    public TCPHandlerFactory(ResponseAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RequestHandler createHandler(Socket socket) {
        return new TCPRequestHandler(adapter, socket);
    }
}
