package com.diku.conversation;

import net.dv8tion.jda.api.entities.Message;

public abstract class BotMessage {

    abstract public void onReply(Message message);
    abstract public Message getMessage();

}
