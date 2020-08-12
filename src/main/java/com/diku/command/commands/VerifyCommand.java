package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.conversation.GuildConversation;
import com.diku.conversation.conversations.VerificationConversation;
import com.diku.ku.Bachelors;
import com.diku.main.Constant;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class VerifyCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);


        if(args.length < 2) {
            channel.sendMessage(user.getAsMention()+" Invalid usage - !verify [password]").queue();
            return;
        }

        if(!GuildConversation.conversationExists(user, channel)) {
            channel.sendMessage(user.getAsMention()+" du var for langsom til at verify til email. Skriv !role igen").queue();
            return;
        }

        if (!(GuildConversation.getConversation(user,channel) instanceof VerificationConversation)) {
            channel.sendMessage(user.getAsMention()+" du var for langsom til at verify til email. Skriv !role igen").queue();
            return;
        }

        VerificationConversation conversation = (VerificationConversation) GuildConversation.getConversation(user,channel);
        String password = conversation.getPassword();
        String email = conversation.getEmail();
        if (args[1].equals(password)) {
            if(Constant.DIKU_EMAILS.contains(email)) {
                guild.addRoleToMember(guild.getMember(user), guild.getRolesByName("Datalog", true).get(0)).queue();
                channel.sendMessage(user.getAsMention()+" du er blevet tilføjet til gruppen: Datalog").queue();

            }else {
                MessageBuilder mb = new MessageBuilder();
                mb.append(user.getAsMention()+" vælg dit fag med !bachelor [fag]\nHer er en liste af fag:\n");

                String subjects = "";
                for(Bachelors bachelor : Bachelors.values()) {
                    subjects+=bachelor.getName()+"\n";
                }
                mb.appendCodeLine(subjects);
                channel.sendMessage(mb.build()).queue();
            }
        }else{
            channel.sendMessage(user.getAsMention()+" forkert koden! Koden er blevet resettet. Du skal skrive !role igen").queue();
        }
        conversation.end();

    }
}
