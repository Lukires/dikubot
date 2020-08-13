package com.diku.listeners;

import com.diku.conversation.GuildConversation;
import com.diku.main.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        Message message = e.getMessage();
        String messageContent = message.getContentRaw();
        if(!messageContent.startsWith("!")) {
            return;
        }

        String command = messageContent.split(" ")[0];

        if (!Main.commands.containsKey(command)) {
            return;
        }

        User user = e.getAuthor();
        MessageChannel channel = e.getChannel();
        Guild guild;

        System.out.println(user.getName()+" executed "+messageContent);

        if(e.getMessage().isFromGuild()) {
            guild = message.getGuild();
        }else if(GuildConversation.conversationExists(user, channel)) {
            guild = GuildConversation.getConversation(user, channel).getGuild();
        }else{
            channel.sendMessage(user.getAsMention()+" jeg er ikke helt sikker hvilken discord server dette tilhører. Skriv kommandoen på en server").queue();
            return;
        }

        Main.commands.get(command).onCommand(e.getAuthor(), guild, e.getChannel(), e.getMessage());

    }
}
