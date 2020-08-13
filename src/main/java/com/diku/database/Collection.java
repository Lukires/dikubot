package com.diku.database;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public abstract class Collection {

    private String collectionName;

    private static Collection instance;
    protected static DB database = Database.getInstance().getDatabase();
    protected static DBCollection collection;

    protected Collection(String collectionName) {
        this.collectionName=collectionName;
        instance=this;
        collection=database.getCollection(collectionName);
    }

    public static Collection getInstance() {
        return instance;
    }

}
