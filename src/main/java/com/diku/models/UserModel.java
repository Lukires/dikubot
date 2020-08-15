package com.diku.models;

import com.diku.database.Cache;
import com.diku.database.Collections;
import com.diku.main.Main;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

public class UserModel extends Model {


    private static Cache<User, UserModel> cache = new Cache<User, UserModel>();
    private static final MongoCollection<Document> collection = Collections.USERS.getCollection();
    private Document userDB;

    private User userDiscord;
    private UserModel(User user) {
        this.userDiscord=user;
        cache.put(user,this, 600);
        userDB = collection.find(Filters.eq("_id", user.getId())).first();

        if (userDB==null) {
            userDB = new Document();
            userDB.append("_id", user.getId());
            userDB.append("email", "");
            userDB.append("verified", false);
            userDB.append("major", "");
            collection.insertOne(userDB);
        }
    }

    private void updateUser(String key, Object value) {
        userDB.append(key, value);
        collection.updateOne(Filters.eq("_id", userDiscord.getId()), new Document("$set", new Document(key, value)));
    }

    public static UserModel getUserModel(String userDiscordID) {
        return getUserModel(Main.jda.getUserById(userDiscordID));
    }


    public static UserModel getUserModel(User user) {
        if (cache.containsKey(user)) {
            return cache.get(user);
        }
        return new UserModel(user);
    }

    public static boolean isEmailVerified(String email) {
        return Collections.USERS.getCollection().find(Filters.and(Filters.eq("email", email), Filters.eq("verified", true))).limit(1).first() == null;
    }

    public User getUserDiscord() {
        return userDiscord;
    }

    public boolean isVerified() {
        return userDB.getBoolean("verified");
    }

    public String getEmail() {
        return userDB.getString("email");
    }

    public String getMajor() {
        return userDB.getString("major");
    }

    public void setVerified(boolean verified) {
        updateUser("verified", verified);
    }

    public void setEmail(String email) {
        updateUser("email", email);
    }

    public void setMajor(String major) {
        updateUser("major", major);
    }


}