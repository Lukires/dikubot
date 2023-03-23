package ninja.diku.command.commands.util;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import ninja.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class TicketCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannelUnion channel, Message message) {

    }

    @Override
    public String getDescription() {
        return "Test Command";
    }

    @Override
    public String getUsage() {
        return "!ticket [Message]";
    }
}
