package com.epam.university.java.core.task031;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

public class ClientImpl implements Client {
    private Socket clientSocket;
    private PrintWriter out;
    private final int port;
    private final String domainName;
    private boolean nullMessage;

    public ClientImpl(int port, String domainName) {
        this.port = port;
        this.domainName = domainName;
    }

    @Override
    public void sendMessage(String message) {
        final Optional<String> receivedMessage = Optional.ofNullable(message);
        if (receivedMessage.isEmpty()) {
            this.nullMessage = true;
            return;
        }

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

            if (nullMessage) {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}