package com.epam.university.java.core.task031;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientImpl implements Client {
    private Socket clientSocket;
    private PrintWriter out;
    private final int port;
    private final String domainName;

    public ClientImpl(int port, String domainName) {
        this.port = port;
        this.domainName = domainName;
    }

    @Override
    public void sendMessage(String message) {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            clientSocket = new Socket(domainName, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            if (out != null) {
                out.close();
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}