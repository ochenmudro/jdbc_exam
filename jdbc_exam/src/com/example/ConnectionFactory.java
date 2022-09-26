package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    String DB_URL = "jdbc:postgresql://localhost:5455/postgresDB";
    String USER = "user";
    String PASS = "admin";
    String driverClassName = "com.mysql.jdbc.Driver";


    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {

    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}
