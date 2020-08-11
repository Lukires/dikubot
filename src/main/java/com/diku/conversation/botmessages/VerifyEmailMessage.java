package com.diku.conversation.botmessages;

import com.diku.conversation.BotMessage;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class VerifyEmailMessage extends BotMessage {

    User user;
    Guild guild;
    public VerifyEmailMessage(User user, Guild Guild) {
        this.user=user;
        this.guild=guild;
    }

    @Override
    public void onReply(Message message) {

    }

    @Override
    public Message getMessage() {
        MessageBuilder mb = new MessageBuilder();
        mb.append("For at sikre os at du går på KU, bedes du indtaste din email, således:");
        mb.appendCodeLine("!email [KU-Email]");
        return null;
    }
}
