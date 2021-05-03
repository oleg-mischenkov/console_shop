package com.mishchenkov.server;

import com.mishchenkov.server.adapter.ResponseAdapter;
import com.mishchenkov.server.request.HTTPHandlerFactory;
import com.mishchenkov.server.request.RequestHandlerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.mishchenkov.constant.Constants.HTTP_SERVER_PORT;
import static com.mishchenkov.constant.Constants.SERVER_HOST;
import static org.mockito.BDDMockito.given;

public class HTTPServerTest {

    @Mock
    private ResponseAdapter adapter;
    private final RequestHandlerFactory handlerFactory;

    public HTTPServerTest() {
        MockitoAnnotations.initMocks(this);
        handlerFactory = new HTTPHandlerFactory(adapter);
    }

    @Test
    public void shouldObtainProductCountFromHttpServer_getProductCountTest() {
        //given
        Server server = new HTTPServer(HTTP_SERVER_PORT, handlerFactory);
        server.startServer();

        String productCount = "{count:20}";
        given(adapter.getCount()).willReturn(productCount);

        //when
        String request = "shop/count";
        String response = getResponse(HTTP_SERVER_PORT, request);

        //then
        Assert.assertEquals(productCount, response);
        server.stopServer();
    }

    @Test
    public void shouldObtainProductItemFromHttpServer_getProductItemTest() {
        //given
        int port = HTTP_SERVER_PORT + 1;

        Server server = new HTTPServer(port, handlerFactory);
        server.startServer();

        String expected = "{name: Introduction to Java Programming and Data Structures, price: 173.32}";
        String sku = "9780134670";
        String productItem = "shop/item?get_info=".concat(sku);
        given(adapter.getItem(sku)).willReturn(expected);

        //when
        String response = getResponse(port, productItem);

        //then
        Assert.assertEquals(expected, response);
        server.stopServer();
    }

    @Test
    public void shouldMakeRequestToTheHttpServerWhenDataIsIncorrect_getProductItemTest() {
        //given
        int port = HTTP_SERVER_PORT + 2;

        Server server = new HTTPServer(port, handlerFactory);
        server.startServer();

        String expected = "Error";
        String productItem = "shop/tra-ta-ta";

        //when
        String response = getResponse(port, productItem);

        //then
        Assert.assertEquals(expected, response);
        server.stopServer();
    }

    private String getResponse(int port, String request) {
        String result = "";
        String host = "http://".concat(SERVER_HOST).concat(":").concat(port + "/").concat(request);

        try (InputStream inputStream = new URL(host)
                .openConnection().getInputStream()) {

            byte[] buf = new byte[128];
            int count;
            while ((count = inputStream.read(buf)) != -1) {
                result += new String(buf, 0, count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}