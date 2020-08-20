package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.ku.Major;
import com.diku.main.Constant;
import com.diku.main.Main;
import com.diku.models.UserModel;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;

import java.util.List;

public class MajorCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);


        if(args.length < 2) {
            MessageBuilder mb = new MessageBuilder();
            mb.append(user.getAsMention()+" Invalid usage - !major [fag]\"\n");

            String subjects = "Fag:\n";
            for(Major major : Major.values()) {
                subjects+=major.getName()+"\n";
            }
            mb.appendCodeLine(subjects);
            channel.sendMessage(mb.build()).queue();
            return;
        }

        UserModel userModel = UserModel.getUserModel(user);
        if (!userModel.isVerified()) {
            channel.sendMessage(user.getAsMention()+" din email er ikke verified. Brug !verify [kode] for at blive verified").queue();
            return;
        }

        boolean dikuEmail = Constant.DIKU_EMAILS.contains(userModel.getEmail());
        boolean machineLearningEmail = Constant.MACHINE_LEARNING_EMAILS.contains(userModel.getEmail());
        boolean datalogiEconomicsEmail = Constant.MACHINE_LEARNING_EMAILS.contains(userModel.getEmail());

        if(dikuEmail || machineLearningEmail || datalogiEconomicsEmail) {
            if(dikuEmail) {
                guild.addRoleToMember(user.getId(), guild.getRolesByName("Datalog", true).get(0)).queue();
                channel.sendMessage(user.getAsMention()+" du går på holdet Datalogi-2020, og er derfor blevet tilføjet til gruppen: Datalog").queue();
                UserModel.getUserModel(user).setMajor("Datalogi-2020");
            }

            if(machineLearningEmail) {
                guild.addRoleToMember(user.getId(), guild.getRolesByName("MachineTeacher", true).get(0)).queue();
                channel.sendMessage(user.getAsMention()+" du går på holdet MachineLearning-2020, og er derfor blevet tilføjet til gruppen: MachineTeacher").queue();
                UserModel.getUserModel(user).setMajor("MachineLearning-2020");
            }

            if(datalogiEconomicsEmail) {
                guild.addRoleToMember(user.getId(), guild.getRolesByName("CBS-Programming", true).get(0)).queue();
                channel.sendMessage(user.getAsMention()+" du går på holdet Datalogi-Økonomi-2020, og er derfor blevet tilføjet til gruppen: CBS-Programming").queue();
                UserModel.getUserModel(user).setMajor("Datalogi-Økonomi-2020");
            }
            return;
        }


        String majorInput = args[1];
        for(Major major : Major.values()) {
            if(major.getName().equalsIgnoreCase(majorInput)) {
                channel.sendMessage(user.getAsMention()+" dit fag er blevet sat til "+major.getName()).queue();

                //I know this is stupid but I can't be bothered to do it right
                /*for(Major majorin : Major.values()) {
                    if (major==majorin) {
                        continue;
                    }
                    guild.removeRoleFromMember(user.getId(), guild.getRolesByName(majorin.getRole().getRole(), true).get(0)).queue();
                }*/

                userModel.setMajor(major.getName());
                guild.addRoleToMember(user.getId(), guild.getRolesByName(major.getRole().getRole(), true).get(0)).queue();
                return;
            }
        }

        MessageBuilder mb = new MessageBuilder();
        mb.append(user.getAsMention()+" Det indtastede fag findes ikke - !major [fag]\"\n");

        String subjects = "Fag:\n";
        for(Major major : Major.values()) {
            subjects+=major.getName()+"\n";
        }
        mb.appendCodeLine(subjects);
        channel.sendMessage(mb.build()).queue();
        return;
    }

    public String getDescription() {
        return "Vælg dit fag. Kræver at du verified";
    }

    @Override
    public String getUsage() {
        return "!major (Fag)";
    }

}
