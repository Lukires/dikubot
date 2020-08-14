package com.diku.database;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public enum Collections {

    USERS("users"),
    EMAILS("emails");


    protected MongoDatabase database = Database.getInstance().getDatabase();
    protected MongoCollection<Document> collection;
    private String collectionName;

    Collections(String collectionName) {
        this.collectionName=collectionName;
        collection=database.getCollection(collectionName);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
