package com.java.examples;

import java.util.ResourceBundle;


public final class Constants {

    private static final ResourceBundle serverResource = ResourceBundle.getBundle("redis");

    public static final String REDIS_SERVER_PASSWORD = serverResource.getString("redis.server.password");

    public static final String REDIS_SERVER_ADDRESS = serverResource.getString("redis.server.address");

    public static final int REDIS_SERVER_PORT = Integer.parseInt(serverResource.getString("redis.server.port"));
}