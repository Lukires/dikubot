package com.diku.database;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public enum Collections {
    USERS("users");


    protected DB database = Database.getInstance().getDatabase();
    protected DBCollection collection;
    private String collectionName;

    Collections(String collectionName) {
        this.collectionName=collectionName;
        collection=database.getCollection(collectionName);
    }

    public DB getDatabase() {
        return database;
    }

    public DBCollection getCollection() {
        return collection;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
