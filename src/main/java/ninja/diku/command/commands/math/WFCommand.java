package ninja.diku.command.commands.math;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import ninja.diku.command.Command;
import ninja.diku.math.VisualMath;

public class WFCommand implements Command {
    @Override
    public void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        String query = removeCommand(message);
        if (query.isEmpty()) {
            channel.sendMessage("Forkert brug! " + getUsage()).queue();
            return;
        }
        try {
            VisualMath math = new VisualMath(query);
            MessageBuilder mb = new MessageBuilder();

            channel.sendFile(math.getImage()).queue();
        } catch (Exception e) {
            channel.sendMessage("Noget gik galt, kunne ikke finde noget :(").queue();
            e.printStackTrace();
            return;
        }
    }

    @Override
    public String getDescription() {
        return "Udregn matematik og andre sjove ting";
    }

    @Override
    public String getUsage() {
        return "!wf (query)";
    }
}
