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
import net.dv8tion.jda.api.entities.*;

public class JoinCommand implements Command {


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

        if(args.length < 3) {
            channel.sendMessage(user.getAsMention()+" Forkert brug - !join [ku-email] [fulde navn]").queue();
            return;
        }

        args[1] = args[1].replace("[","").replace("]","");

        if (!args[1].endsWith(".ku.dk") && !args[1].endsWith("@ku.dk")) {
            channel.sendMessage(user.getAsMention()+" Ikke en gyldig KU-email, usage - !join [ku-email] [fulde navn]").queue();
            return;
        }

        if (UserModel.isEmailVerified(args[1])) {
            channel.sendMessage(user.getAsMention()+" denne email er allerede blevet brugt").queue();
            return;
        }

        if(message.isFromGuild()) {
            //We censor the email if the command is executed on a server
            //message.editMessage(args[0] + " " + args[1] + "******@" + args[2].split("@")[1]);
            message.delete().queue();
        }

        StringBuilder name = new StringBuilder();
        for (int i = 2; i<args.length; i++) {
            name.append(args[i]);
        }

        Member member = guild.retrieveMember(user).complete();
        member.modifyNickname(name.toString()).queue();

        MessageBuilder mb = new MessageBuilder();
        mb.append(user.getAsMention()+" der er blevet sendt en kode til din KU-email: "+"skjult@" + args[1].split("@")[1]).append("\n");
        mb.appendCodeLine("Når du har fundet koden, skal du skrive: !verify [Kode]");
        channel.sendMessage(mb.build()).queue();

        String password = Util.generateString();

        botEmail.sendEmail(new Email(args[2]), "DIKU Discord", new Content("text/plain", "Her er din kode til discord: "+ password));
        GuildConversation.addConversation(new VerificationConversation(user, guild, password, args[1]));

        return;

    }

    public String getDescription() {
        return "Bliv en del af Discord serveren";
    }

    @Override
    public String getUsage() {
        return "!join [ku-email] [fulde navn]";
    }

}
