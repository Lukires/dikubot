package com.diku.conversation;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Conversation {


    abstract public void build();

    private static HashMap<User, HashMap<MessageChannel, Conversation>> conversations = new HashMap<User, HashMap<MessageChannel, Conversation>>();

    protected User user;
    protected MessageChannel channel;
    private ArrayList<BotMessage> messages = new ArrayList<BotMessage>();
    protected int messageIndex = 0;

    public Conversation(User user, MessageChannel channel) {
        this.user = user;
        this.channel=channel;

        HashMap<MessageChannel, Conversation> channelConversationHashMap = conversations.containsKey(user)?conversations.get(user):new HashMap<MessageChannel, Conversation>();
        channelConversationHashMap.put(channel, this);
        conversations.put(user, channelConversationHashMap);
    }

    public void addMessage(BotMessage message) {
        this.messages.add(message);
    }

    public BotMessage getMessage(int index) {
        return this.messages.get(index);
    }


    public User getUser() {
        return user;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public void end() {
        conversations.get(user).remove(channel);
    }

    public static boolean conversationExists(User user, MessageChannel channel) {
        return conversations.containsKey(user)?conversations.get(user).containsKey(channel):false;
    }

    public static Conversation getConversation(User user, MessageChannel channel) {
        return conversations.get(user).get(channel);
    }
}
