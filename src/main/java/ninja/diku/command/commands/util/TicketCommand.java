package ninja.diku.command.commands.util;

import ninja.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class TicketCommand implements Command {

    /**
     *  Is currently not in use
     *
     * @param  user     The user object.
     * @param  guild    The guild object, the user belongs to.
     * @param  channel  The channel object, the message was written in.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         Command
     */
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {

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
