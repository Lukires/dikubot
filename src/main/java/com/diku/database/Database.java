package com.diku.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private final String user = "datalog";
    private final String dbName = "dikubot";
    private final String pass = "kudatalogi";
    private final String ip = "127.0.0.1";
    private final String port = "3306";
    private static final Database database = new Database();
    private Connection connection;

    private Database() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mariadb://"+ip+":"+port+"/"+dbName,user,pass);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getDatabase() {
        return database;
    }

    public Connection getConnection() {
        return connection;
    }

}
