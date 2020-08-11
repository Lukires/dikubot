package com.diku.conversation.conversations;

import com.diku.conversation.Conversation;
import com.diku.conversation.botmessages.WelcomeMessage;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class RoleConversation extends Conversation {

    public RoleConversation(User user, MessageChannel channel) {
        super(user, channel);
    }

    @Override
    public void build() {
        addMessage(new WelcomeMessage(user));
    }
}
