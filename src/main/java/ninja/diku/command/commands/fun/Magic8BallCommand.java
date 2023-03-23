package ninja.diku.command.commands.fun;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import ninja.diku.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.Random;

public class Magic8BallCommand implements Command {

    final static String[] answers = new String[] {
            "Det er sikkert.",
            "Det er bestemt.",
            "Uden tvivl.",
            "Ja helt sikkert.",
            "Det kan du tro på.",
            "Som jeg ser det, ja.",
            "Højst sandsynlig.",
            "Det lover godt.",
            "Ja.",
            "Skiltene peger mod et ja.",
            "Svar uklar, prøv igen.",
            "Spørg igen senere.",
            "Bedre ikke at fortælle dig nu.",
            "Kan ikke forudsiges nu.",
            "Koncentrer dig og spørg igen.",
            "Regn ikke med det.",
            "Mit svar er nej.",
            "Mine kilder siger nej.",
            "Ser ikke for godt ud.",
            "Meget tvivlsomt."};

    /**
     * This command is a textbased Magic 8 Ball.
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

        if (getArgs(message).length > 1) {
            Random rand = new Random();
            channel.sendMessage(answers[rand.nextInt(answers.length)] + "").queue();
        }
        return;
    }

    public String getDescription() {
        return "Magic 8 Ball kan svarer dig på spørgsmål om fremtiden eller give dig råd.";
    }

    @Override
    public String getUsage() {
        return "!magic8ball spørgsmål";
    }
}
