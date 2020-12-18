package ninja.diku.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public enum Collections {

    USERS("users"),
    TICKETS("tickets");


    protected final MongoDatabase database = Database.getInstance().getDatabase();
    protected MongoCollection<Document> collection;
    private String collectionName;

    Collections(String collectionName) {
        this.collectionName=collectionName;
        collection=database.getCollection(collectionName);
    }

    /**
     * Get the database represented by the enum
     * @see Database
     * @return MongoDatabase
     */
    public MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Get the collection represented by the enum
     * @see Database
     * @return MongoCollection<Document>
     */
    public MongoCollection<Document> getCollection() {
        return collection;
    }

    /**
     * Get the name of the collection represented by the enum
     * @return String
     */
    public String getCollectionName() {
        return collectionName;
    }
}
