package com.diku.conversation;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GuildConversation {


    /*

    The whole GuildConversation concept is pretty bad, will probably improve it or something in the future

     */

    private static HashMap<User, HashMap<MessageChannel, GuildConversation>> conversations = new HashMap<User, HashMap<MessageChannel, GuildConversation>>();

    protected User user;
    protected MessageChannel channel;
    protected Guild guild;

    public GuildConversation(User user, MessageChannel channel, Guild guild) {
        this.user = user;
        this.channel=channel;
        this.guild=guild;
    }

    public void add() {
        HashMap<MessageChannel, GuildConversation> channelConversationHashMap = conversations.containsKey(user)?conversations.get(user):new HashMap<MessageChannel, GuildConversation>();
        channelConversationHashMap.put(channel, this);
        conversations.put(user, channelConversationHashMap);
    }

    public void end() {
        conversations.get(user).remove(channel);
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

    public MessageChannel getChannel() {
        return channel;
    }

    public Guild getGuild() {
        return guild;
    }

    public static boolean conversationExists(User user, MessageChannel channel) {
        return conversations.containsKey(user)?conversations.get(user).containsKey(channel):false;
    }

    public static GuildConversation getConversation(User user, MessageChannel channel) {
        return conversations.get(user).get(channel);
    }
}
