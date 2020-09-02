package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.conversation.GuildConversation;
import com.diku.conversation.conversations.VerificationConversation;
import com.diku.database.Collections;
import com.diku.ku.Major;
import com.diku.ku.Roles;
import com.diku.main.Constant;
import com.diku.models.UserModel;
import com.mongodb.client.model.Filters;
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

        if (UserModel.isEmailVerified(email)) {
            channel.sendMessage(user.getAsMention()+" denne email er allerede blevet verified").queue();
            return;
        }


        if (!args[1].equals(password)) {
            channel.sendMessage(user.getAsMention()+" forkert kode! Koden er blevet resettet. Du skal skrive !role igen").queue();
            conversation.end();
            return;
        }

        UserModel userModel = UserModel.getUserModel(user);
        userModel.setEmail(email);
        userModel.setVerified(true);

        boolean datalogiEmail = Constant.DATALOGI_EMAILS.contains(email);
        boolean machineLearningEmail = Constant.MACHINE_LEARNING_EMAILS.contains(email);
        boolean datalogiEconomicsEmail = Constant.DATALOGI_ECONOMICS_EMAILS.contains(email);
        boolean dikuEmail = Constant.DIKU_EMAILS.contains(email);

        if(dikuEmail) {
            guild.addRoleToMember(user.getId(), guild.getRolesByName("DIKU", true).get(0)).queue();
            channel.sendMessage(user.getAsMention()+" din email er verified og du er blevet tilføjet til gruppen: DIKU").queue();
            UserModel.getUserModel(user).setMajor("DIKU");
            return;
        }

        if(!machineLearningEmail && !datalogiEmail && !datalogiEconomicsEmail) {
            MessageBuilder mb = new MessageBuilder();
            mb.append(user.getAsMention()+" din email er blevet verified. Vælg dit fag med !major [fag]\nHer er en liste af fag:\n");

            String subjects = "";
            for(Major major : Major.values()) {
                subjects+=major.getName()+"\n";
            }
            mb.appendCodeLine(subjects);
            channel.sendMessage(mb.build()).queue();
            return;
        }

        if(datalogiEmail) {

            guild.addRoleToMember(user.getId(), guild.getRolesByName(Roles.DATALOG.getRole(), true).get(0)).queue();
            channel.sendMessage(user.getAsMention()+" din email er verified og du er blevet tilføjet til gruppen: Datalog").queue();
            UserModel.getUserModel(user).setMajor("Datalogi-2020");

        }
        if(machineLearningEmail) {
            guild.addRoleToMember(user.getId(), guild.getRolesByName(Roles.MACHINETEACHER.getRole(), true).get(0)).queue();
            channel.sendMessage(user.getAsMention() + " din email er verified og du er blevet tilføjet til gruppen: Machine Teachers").queue();
            UserModel.getUserModel(user).setMajor("MachineLearning-2020");
        }

        if(datalogiEconomicsEmail) {
            guild.addRoleToMember(user.getId(), guild.getRolesByName(Roles.CBS_PROGRAMMING.getRole(), true).get(0)).queue();
            channel.sendMessage(user.getAsMention() + " din email er verified og du er blevet tilføjet til gruppen: CBS-Programming").queue();
            UserModel.getUserModel(user).setMajor("Datalogi-Økonomi-2020");
        }

    }

    public String getDescription() {
        return "Verify din KU email";
    }

    public String getUsage() {
        return "!verify [Kode]";
    }
}
