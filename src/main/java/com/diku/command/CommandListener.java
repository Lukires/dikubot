package com.diku.command;

import com.diku.conversation.GuildConversation;
import com.diku.main.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Date;

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

        if (user.isBot()) {
            return;
        }

        MessageChannel channel = e.getChannel();
        Guild guild;

        System.out.println(new Date(System.currentTimeMillis()).toString()+" - User "+user.getName()+" ("+user.getAsTag()+") executed "+messageContent);

        if(e.getMessage().isFromGuild()) {
            guild = message.getGuild();
        }else if(GuildConversation.conversationExists(user)) {
            guild = GuildConversation.getConversation(user).getGuild();
        }else{
            channel.sendMessage(user.getAsMention()+" jeg er ikke helt sikker hvilken discord server dette tilhører. Skriv kommandoen på en server").queue();
            return;
        }

        Main.commands.get(command).onCommand(user, guild, e.getChannel(), e.getMessage());

    }
}
