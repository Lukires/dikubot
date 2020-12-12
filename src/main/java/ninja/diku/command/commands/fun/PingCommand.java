package ninja.diku.command.commands.fun;

import ninja.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class PingCommand implements Command {

    /**
     * !ping returns Pong!
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
        channel.sendMessage("Pong!").queue();
    }

    public String getDescription() {
        return "Pong!";
    }

    @Override
    public String getUsage() {
        return "!ping";
    }
}
