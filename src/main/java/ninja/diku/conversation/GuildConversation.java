package ninja.diku.conversation;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;

public class GuildConversation {


    /*

    The whole GuildConversation concept is pretty bad, will probably improve it or something in the future

     */

    private static HashMap<User, GuildConversation> conversations = new HashMap<User, GuildConversation>();

    protected User user;
    protected Guild guild;

    public GuildConversation(User user, Guild guild) {
        this.user = user;
        this.guild=guild;
    }

    public void add() {
        conversations.put(user, this);
    }

    public void end() {
        conversations.remove(user);
    }


    public static void addConversation(GuildConversation conversation) {
        conversation.add();
    }

    public static void endConversation(GuildConversation conversation) {
        conversation.end();
    }

    public User getUser() {
        return user;
    }

    public Guild getGuild() {
        return guild;
    }

    public static boolean conversationExists(User user) {
        return conversations.containsKey(user);
    }

    public static GuildConversation getConversation(User user) {
        return conversations.get(user);
    }
}
