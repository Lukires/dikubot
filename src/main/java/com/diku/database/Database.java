package com.diku.database;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;


public class Database {


    private static final String hostName = "mongodb://localhost:27017";
    private static final String dbName = "dikubot";

    private static final Database instance = new Database();
    private MongoClient mongoClient;
    private DB database;

    private Database() {
        try {
            mongoClient = new MongoClient(new MongoClientURI(hostName));
            database = mongoClient.getDB(dbName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return instance;
    }

    public MongoClient getMongoClient() {
        return mongoClient;

    }

    public DB getDatabase() {
        return database;
    }
}
