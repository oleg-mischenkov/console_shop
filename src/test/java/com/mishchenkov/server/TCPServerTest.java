package com.mishchenkov.server;

import com.mishchenkov.server.adapter.ResponseAdapter;
import com.mishchenkov.server.request.RequestHandlerFactory;
import com.mishchenkov.server.request.TCPHandlerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static com.mishchenkov.constant.Constants.SERVER_HOST;
import static com.mishchenkov.constant.Constants.TCP_SERVER_PORT;
import static org.mockito.BDDMockito.given;

public class TCPServerTest {

    @Mock
    private ResponseAdapter adapter;
    private final RequestHandlerFactory handlerFactory;

    public TCPServerTest() {
        MockitoAnnotations.initMocks(this);
        handlerFactory = new TCPHandlerFactory(adapter);
    }

    @Test
    public void shouldObtainProductCountFromTcpServer_getProductCountTest() {
        //given
        Server server = new TCPServer(TCP_SERVER_PORT, handlerFactory);
        server.startServer();

        String productCount = "10";
        given(adapter.getCount()).willReturn(productCount);

        //when
        String request = "get count";
        String response = getResponse(TCP_SERVER_PORT, request);

        //then
        Assert.assertEquals(productCount, response);
        server.stopServer();
        getResponse(TCP_SERVER_PORT, request);
    }

    @Test
    public void shouldObtainProductItemFromTcpServer_getProductItemTest() {
        //given
        int port = TCP_SERVER_PORT + 1;

        Server server = new TCPServer(port, handlerFactory);
        server.startServer();

        String expected = "Banana | 10.00";
        String sku = "007";
        String productItem = "get item=".concat(sku);
        given(adapter.getItem(sku)).willReturn(expected);

        //when
        String response = getResponse(port, productItem);

        //then
        Assert.assertEquals(expected, response);
        server.stopServer();
        getResponse(port, productItem);
    }

    @Test
    public void shouldMakeRequestToTheTcpServerWhenDataIsIncorrect_getProductItemTest() {
        //given
        int port = TCP_SERVER_PORT + 2;

        Server server = new TCPServer(port, handlerFactory);
        server.startServer();

        String expected = "Error! [get product=007]";
        String productItem = "get product=007";

        //when
        String response = getResponse(port, productItem);

        //then
        Assert.assertEquals(expected, response);
        server.stopServer();
        getResponse(port, productItem);
    }

    private String getResponse(int port, String request) {
        String result = null;

        try (Socket socket = new Socket(SERVER_HOST, port)) {

            try (
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
            ) {
                outputStream.writeUTF(request);
                outputStream.flush();

                result = inputStream.readUTF();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}