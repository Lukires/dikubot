package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.ku.Major;
import com.diku.ku.Roles;
import com.diku.main.Constant;
import com.diku.main.Main;
import com.diku.models.UserModel;
import com.diku.ticket.tickets.MajorTicket;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;

import java.util.List;
import java.util.UUID;

public class MajorCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);


        if(args.length < 2) {
            MessageBuilder mb = new MessageBuilder();
            mb.append(user.getAsMention()).append(" Invalid usage - !major [fag]\"\n");

            StringBuilder subjects = new StringBuilder("Fag:\n");
            for(Major major : Major.values()) {
                subjects.append(major.getName()).append("\n");
            }
            mb.append("```").append(subjects.toString()).append("```");
            channel.sendMessage(mb.build()).queue();
            return;
        }

        UserModel userModel = UserModel.getUserModel(user);
        if (!userModel.isVerified()) {
            channel.sendMessage(user.getAsMention()+" din email er ikke verified. Brug !verify [kode] for at blive verified").queue();
            return;
        }


        String majorInput = args[1].replace("[","").replace("]","");;
        for(Major major : Major.values()) {
            if(major.getName().equalsIgnoreCase(majorInput)) {
                channel.sendMessage(user.getAsMention()+" du har anmodet om at få sat din major til  "+major.getName()+ ". En moderator vil tage et kig på din anmodning").queue();

                //userModel.setMajor(major.getName());
                //guild.addRoleToMember(user.getId(), guild.getRolesByName(major.getRole().getRole(), true).get(0)).queue();

                //THIS IS TESTING
                UUID uuid = UUID.randomUUID();
                new MajorTicket(guild, user, uuid, major).activate();
                System.out.printf("%s %s %s %s%n", guild.toString(), user.getId(), uuid.toString(), major.name());
                return;
            }
        }

        MessageBuilder mb = new MessageBuilder();
        mb.append(user.getAsMention()).append(" Det indtastede fag findes ikke - !major [fag]\"\n");

        StringBuilder subjects = new StringBuilder("Fag:\n");
        for(Major major : Major.values()) {
            subjects.append(major.getName()).append("\n");
        }
        mb.appendCodeLine(subjects.toString());
        channel.sendMessage(mb.build()).queue();
    }

    public String getDescription() {
        return "Vælg dit fag. Kræver at du verified";
    }

    @Override
    public String getUsage() {
        return "!major (Fag)";
    }

}
