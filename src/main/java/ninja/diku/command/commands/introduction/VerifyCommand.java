package ninja.diku.command.commands.introduction;

import ninja.diku.command.Command;
import ninja.diku.conversation.GuildConversation;
import ninja.diku.conversation.conversations.VerificationConversation;
import ninja.diku.ku.Major;
import ninja.diku.ku.Roles;
import ninja.diku.main.Constant;
import ninja.diku.models.UserModel;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class VerifyCommand implements Command {

    /**
     * This command is used to verify an email address by having the user type a password previously sent by using the Join command
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @param  channel  The channel object, the message was written in.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         Command
     * @see         JoinCommand
     */
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);


        if(args.length < 2) {
            channel.sendMessage(user.getAsMention()+" Forkert brug - !verify [password]").queue();
            return;
        }

        if(!GuildConversation.conversationExists(user)) {
            channel.sendMessage(user.getAsMention()+" du var for langsom til at verify til email. Skriv !join igen").queue();
            return;
        }

        if (!(GuildConversation.getConversation(user) instanceof VerificationConversation)) {
            channel.sendMessage(user.getAsMention()+" du var for langsom til at verify til email. Skriv !join igen").queue();
            return;
        }

        VerificationConversation conversation = (VerificationConversation) GuildConversation.getConversation(user);

        if(!conversation.getGuild().equals(guild)) {
            channel.sendMessage(user.getAsMention()+" du er allerede i gang med at verify din email på en anden server.").queue();
            return;
        }

        String password = conversation.getPassword();
        String email = conversation.getEmail();

        if (UserModel.isEmailVerified(email)) {
            channel.sendMessage(user.getAsMention()+" denne email er allerede blevet verified").queue();
            return;
        }


        args[1] = args[1].replace("[","").replace("]","");

        if (!args[1].equals(password)) {
            channel.sendMessage(user.getAsMention()+" forkert kode! Koden er blevet resettet. Du skal skrive !join igen").queue();
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
            mb.append(user.getAsMention()).append(" din email er blevet verified. Vælg dit fag med !major [fag]\nHer er en liste af fag:\n");

            StringBuilder subjects = new StringBuilder("```");
            for(Major major : Major.values()) {
                subjects.append(major.getName()).append("\n");
            }
            mb.append(subjects).append("```");
            channel.sendMessage(mb.build()).queue();
            guild.addRoleToMember(user.getId(), guild.getRolesByName("KU", true).get(0)).queue();
            guild.removeRoleFromMember(user.getId(), guild.getRolesByName("Guest", true).get(0)).queue();
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
