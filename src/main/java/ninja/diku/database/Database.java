package ninja.diku.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


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

    /**
     * Returns a singleton database instance reference
     * @return Database
     */
    public static Database getInstance() {
        return instance;
    }

    /**
     * Get the mongodb client
     * @return MongoClient
     */
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    /**
     * This is the "real" database. This is the instance you use to interact with the data in the mongo database.
     * @return MongoDatabase
     * @see Collections
     */
    public MongoDatabase getDatabase() {
        return database;
    }
}
