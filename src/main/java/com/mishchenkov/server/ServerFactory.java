package com.mishchenkov.server;

import com.mishchenkov.server.adapter.HTTPResponseAdapter;
import com.mishchenkov.server.adapter.TCPResponseAdapter;
import com.mishchenkov.server.request.HTTPHandlerFactory;
import com.mishchenkov.server.request.TCPHandlerFactory;
import com.mishchenkov.service.ProductService;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import static com.mishchenkov.constant.Constants.HTTP_SERVER_PORT;
import static com.mishchenkov.constant.Constants.TCP_SERVER_PORT;

public final class ServerFactory {

    private final static Map<Class<? extends Server>, Function<ProductService, Server>> SERVERS;

    static {
        SERVERS = new TreeMap<>(Comparator.comparing(Class::getSimpleName, String::compareTo));
        SERVERS.put(
                TCPServer.class,
                service -> new TCPServer(TCP_SERVER_PORT, new TCPHandlerFactory(new TCPResponseAdapter(service)))
        );
        SERVERS.put(
                HTTPServer.class,
                service -> new HTTPServer(HTTP_SERVER_PORT, new HTTPHandlerFactory(new HTTPResponseAdapter(service)))
        );
    }

    private ServerFactory() {}

    public static Server getServer(Class<? extends Server> serverClass, ProductService service) {
        return SERVERS.get(serverClass).apply(service);
    }

}
