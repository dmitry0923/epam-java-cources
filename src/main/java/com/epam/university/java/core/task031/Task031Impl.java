package com.epam.university.java.core.task031;

public class Task031Impl implements Task031 {
    private final int port;
    private final int maxConnections;
    private final String domainName;

    /**
     * Setting constructor with port number, max connections and domain name.
     */
    public Task031Impl() {
        port = 8090;
        maxConnections = 2;
        domainName = "localhost";
    }

    @Override
    public Client createClient() {
        return new ClientImpl(port, domainName);
    }

    @Override
    public Server createServer() {
        return new ServerImpl(port, maxConnections, domainName);
    }
}