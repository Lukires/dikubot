package com.diku.database;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;


public class Database {


    private static final String hostName = "mongodb://localhost:27017";
    private static final String dbName = "dikubot";

    private static final Database instance = new Database();
    private MongoClient mongoClient;
    private MongoDatabase database;

    private Database() {
        mongoClient = new MongoClient(new MongoClientURI(hostName));
        database = mongoClient.getDatabase(dbName);
    }

    public static Database getInstance() {
        return instance;
    }

    public MongoClient getMongoClient() {
        return mongoClient;

    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
