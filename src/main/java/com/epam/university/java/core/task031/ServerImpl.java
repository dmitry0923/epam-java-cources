package com.epam.university.java.core.task031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServerImpl implements Server {
    private final List<Socket> connectedClients;
    private final LinkedList<String> receivedMessages;
    private final int port;
    private final int maxConnections;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private final String domainName;
    private InetAddress inetAddress;

    /**
     * Implementing server with port, maxConnections and domain name.
     */
    public ServerImpl(int port, int maxConnections, String domainName) {
        this.port = port;
        this.maxConnections = maxConnections;
        this.domainName = domainName;
        connectedClients = new ArrayList<>();
        receivedMessages = new LinkedList<>();
    }

    @Override
    public String readMessage() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Socket socket : connectedClients) {
            try {
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                while (in.ready()) {
                    String messageFromClient = in.readLine();
                    receivedMessages.add(messageFromClient);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!receivedMessages.isEmpty()) {
            return receivedMessages.pollLast();
        }

        return "";
    }

    @Override
    public void start() {
        try {
            inetAddress = InetAddress.getByName(domainName);
            serverSocket = new ServerSocket(port, maxConnections, inetAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (!serverSocket.isClosed()) {
                try {
                    clientSocket = serverSocket.accept();
                    connectedClients.add(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void stop() {
        try {
            if (in != null) {
                in.close();
            }
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}