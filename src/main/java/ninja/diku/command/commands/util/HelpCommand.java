package ninja.diku.command.commands.util;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import ninja.diku.command.Command;
import ninja.diku.main.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class HelpCommand implements Command {

    /**
     * The help command can be used like so "!help !command" where "!command" is some command. If no command is
     * given then it will return a list of all the commands.
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @param  channel  The channel object, the message was written in.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         Command
     */

    @Override
    public void onCommand(User user, Guild guild, MessageChannelUnion channel, Message message) {
        String[] args = getArgs(message);
        if(args.length>1) {
            String command = args[1];
            command = command.startsWith("!")?command:"!"+command;
            if (!Main.commands.containsKey(command)) {
                channel.sendMessage(user.getAsMention()+" kunne ikke finde kommandoen "+args[1]).queue();
                return;
            }
            channel.sendMessage(user.getAsMention()+" usage: "+Main.commands.get(command).getUsage()).queue();
            return;
        }

        MessageCreateBuilder mb = new MessageCreateBuilder();
        mb.addContent(user.getAsMention()).addContent(" brug for hjælp? Her er en liste af alle mine kommandoer! \n");
        StringBuilder commands = new StringBuilder();
        for(String command : Main.commands.keySet()) {
            String commandUpper = command.substring(0, 2).toUpperCase() + command.substring(2);
            commands.append(commandUpper).append(" - '").append(Main.commands.get(command).getDescription().toLowerCase()).append("'\n");
        }
        mb.addContent("```prolog\n").addContent(String.valueOf(commands)).addContent("```");
        channel.sendMessage(mb.build()).queue();
    }

    public String getDescription() {
        return "Viser dig alle kommandoer";
    }

    @Override
    public String getUsage() {
        return "!help (Command)";
    }


}
