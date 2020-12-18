package ninja.diku.models;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ninja.diku.database.Collections;
import ninja.diku.main.Constant;
import ninja.diku.main.Main;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

import java.util.Random;

public class UserModel extends Model<User> {

    public UserModel(User user) {
        super(user);
    }

    @Override
    public MongoCollection<Document> getCollection() {
        return Collections.USERS.getCollection();
    }

    @Override
    public String getID() {
        return object.getId();
    }

    protected Document init() {
        if (document==null) {
            document = new Document();
            document.append("_id", object.getId());
            document.append("email", "");
            document.append("verified", false);
            document.append("major", "");
            document.append("music_banned", false);
        }
        return document;
    }

    @Deprecated
    public static UserModel getUserModel(String userDiscordID) {
        return getUserModel(Main.jda.getUserById(userDiscordID));
    }

    public static void resetUserByID(String id) {
        Collections.USERS.getCollection().deleteOne(Filters.eq("_id", id));
    }


    @Deprecated
    public static UserModel getUserModel(User user) {
        return new UserModel(user);
    }

    public static boolean isEmailVerified(String email) {
        email = BCrypt.withDefaults().hashToString(Constant.HASH_COST, email.toCharArray());
        return Collections.USERS.getCollection().find(Filters.and(Filters.eq("email", email), Filters.eq("verified", true))).limit(1).first() != null;
    }

    public User getUserDiscord() {
        return object;
    }

    public boolean isVerified() {
        return document.getBoolean("verified");
    }

    public String getEmail() {
        return document.getString("email");
    }

    public String getMajor() {
        return document.getString("major");
    }

    public void setVerified(boolean verified) {
        update("verified", verified);
    }

    public void setEmail(String email) {
        update("email", BCrypt.withDefaults().hashToString(Constant.HASH_COST, email.toCharArray()));
    }

    public void setMajor(String major) {
        update("major", major);
    }

    public boolean isMusicBanned() {
        return document.getBoolean("music_banned", false);
    }

    public void setMusicBanned(boolean banned) {
        update("music_banned", banned);
    }

    public double getProdigyPercentile() {
        Double percentile = document.getDouble("prodigypercentile");
        if(percentile == null || percentile >= 99.9 || percentile <= 0.001) {
            percentile = (new Random().nextGaussian()*16)+50;
            percentile = percentile>=99.9?(new Random().nextGaussian()*16)+50:percentile<=0.1?0.001:(new Random().nextGaussian()*16)+50;
            setProdigyPercentile(percentile);
            return getProdigyPercentile();
        }
        return percentile;

    }

    public void setProdigyPercentile(double percentile) {
        update("prodigypercentile", percentile);
    }


}
