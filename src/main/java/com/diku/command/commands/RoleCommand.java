package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.conversation.GuildConversation;
import com.diku.conversation.conversations.VerificationConversation;
import com.diku.email.BotEmail;
import com.diku.main.Util;
import com.diku.models.UserModel;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class RoleCommand implements Command {


    BotEmail botEmail = BotEmail.getInstance();

    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);

        UserModel userModel = UserModel.getUserModel(user);
        if (userModel.isVerified()) {
            if (userModel.getMajor().equals("")) {
                channel.sendMessage(user.getAsMention()+" din email er allerede verified. Du kan vælge dit fag med kommandoen **!major** ").queue();
            }else{
                channel.sendMessage(user.getAsMention()+" din email er allerede verified. ").queue();
            }
            return;
        }

        if(args.length < 2) {
            channel.sendMessage(user.getAsMention()+" Invalid usage - !role ku [ku-email] OR !role guest").queue();
            return;
        }

        if(args[1].equalsIgnoreCase("ku")) {
            if(args.length < 3) {
                channel.sendMessage(user.getAsMention()+" Invalid usage - !role ku [ku-email]").queue();
                return;
            }
            if (!args[2].endsWith(".ku.dk") && !args[2].endsWith("@ku.dk")) {
                channel.sendMessage(user.getAsMention()+" Ikke en gyldig KU-email, usage - !role ku [ku-email]").queue();
                return;
            }

            if (UserModel.isEmailVerified(args[2])) {
                channel.sendMessage(user.getAsMention()+" denne email er allerede blevet verified").queue();
                return;
            }

            if(message.isFromGuild()) {
                //We censor the email if the command is executed on a server
                //message.editMessage(args[0] + " " + args[1] + "******@" + args[2].split("@")[1]);
                message.delete().queue();
            }

            MessageBuilder mb = new MessageBuilder();
            mb.append(user.getAsMention()+" der er blevet sendt en kode til din KU-email: "+"hidden@" + args[2].split("@")[1]);
            mb.appendCodeLine("\nNår du har fundet koden, skal du skrive: !verify [Kode]");
            channel.sendMessage(mb.build()).queue();

            String password = Util.generateString();

            botEmail.sendEmail(new Email(args[2]), "DIKU Discord", new Content("text/plain", "Her er din kode til discord: "+ password));
            GuildConversation.addConversation(new VerificationConversation(user, channel, guild, password, args[2]));

            return;
        }else if(args[1].equalsIgnoreCase("guest")) {
            channel.sendMessage(user.getAsMention()+" denne discord er kun for studerende på KU. Hvis du stadig ønsker adgang, bedes du kontakte en moderator").queue();
            return;
        }
        channel.sendMessage(user.getAsMention()+" Invalid usage - !role ku [ku-email] OR !role guest").queue();
        return;

    }
}
