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

    /**
     * Initialise a conversation between the bot and the user, in the specified guild. Used to keep track of what guild the user is connected to,
     * if they were to send the bot a private message. It is also used to keep track of verification passwords.
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     */
    public GuildConversation(User user, Guild guild) {
        this.user = user;
        this.guild=guild;
    }

    /**
     * Add the conversation object to the static conversation list. This is usually used after a conversation has been created
     */
    public void add() {
        conversations.put(user, this);
    }

    /**
     * Remove the conversation object from the static conversation list. This is usually used after a conversation has ended or seized its purpose.
     */
    public void end() {
        conversations.remove(user);
    }

    /**
     * Add the conversation to the conversations list
     * @param conversation
     */
    public static void addConversation(GuildConversation conversation) {
        conversation.add();
    }

    /**
     * Remove the conversation from the conversations list
     * @param conversation
     */
    public static void endConversation(GuildConversation conversation) {
        conversation.end();
    }


    /**
     * Get the user in the conversation
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Get the guild in the conversation
     * @return Guild
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * Checks whehter a user is currently in a conversation
     * @param user
     * @return boolean
     */
    public static boolean conversationExists(User user) {
        return conversations.containsKey(user);
    }

    /**
     * Gets the conversation the user is currently in
     * @param user
     * @return GuildConversation
     */
    public static GuildConversation getConversation(User user) {
        return conversations.get(user);
    }
}
