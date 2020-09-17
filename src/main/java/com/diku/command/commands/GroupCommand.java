package com.diku.command.commands;

import com.diku.command.Command;
import com.diku.ku.Roles;
import com.diku.main.Main;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class GroupCommand implements Command {

    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String[] args = getArgs(message);

        if(args.length<2) {
            channel.sendMessage(user.getAsMention()+" forkert brug !group join/leave/list [Gruppe]").queue();
            sendGroups(user,channel);
            return;
        }

        if(args[1].equalsIgnoreCase("list")) {
            sendGroups(user,channel);
            return;
        }

        if (!args[1].equalsIgnoreCase("join") && !args[1].equalsIgnoreCase("leave")) {
            channel.sendMessage(user.getAsMention()+" forkert brug !group join/leave/list [Gruppe]").queue();
            sendGroups(user,channel);
            return;
        }

        if (args.length<3) {
            channel.sendMessage(user.getAsMention()+" invalid usage. !group join/leave/list [Group]").queue();
            sendGroups(user,channel);
            return;
        }

        String roleName = args[2];
        if (!Roles.containsRoleName(roleName)) {
            channel.sendMessage(user.getAsMention()+" Kunne ikke finde gruppen.").queue();
            sendGroups(user,channel);
            return;
        }

        Roles role = Roles.getRoleByRoleName(roleName);
        if(!role.isSelectable()) {
            channel.sendMessage(user.getAsMention()+" Kunne ikke finde gruppen.").queue();
            sendGroups(user,channel);
            return;
        }
        boolean joinOrLeave = args[1].equalsIgnoreCase("join") || (!args[1].equalsIgnoreCase("leave"));

        if(joinOrLeave) {
            guild.addRoleToMember(user.getId(), guild.getRolesByName(role.getRole(), true).get(0)).queue();
            channel.sendMessage(user.getAsMention() + " du er blevet tilføjet til "+args[2]).queue();
        }else{
            guild.removeRoleFromMember(user.getId(), guild.getRolesByName(role.getRole(), true).get(0)).queue();
            channel.sendMessage(user.getAsMention() + " du er blevet fjernet fra "+args[2]).queue();
        }

    }

    private void sendGroups(User user, MessageChannel channel) {
        MessageBuilder mb = new MessageBuilder();
        mb.append("Liste af vores roller \n");
        StringBuilder roles = new StringBuilder();
        for(Roles role : Roles.values()) {
            if (role.isSelectable()) {
                roles.append(roles).append(" - ").append(role.getRole().toLowerCase()).append("\n");
            }
        }
        mb.appendCodeLine(roles.toString());
        channel.sendMessage(mb.build()).queue();
    }

    @Override
    public String getDescription() {
        return "Bliv del af en gruppe";
    }

    @Override
    public String getUsage() {
        return "!group [join/leave/list] [Gruppe]";
    }
}
