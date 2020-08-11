package com.diku.conversation.botmessages;

import com.diku.conversation.BotMessage;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class WelcomeMessage extends BotMessage {

    User user;
    Guild guild;
    public WelcomeMessage(User user, Guild Guild) {
        this.user=user;
        this.guild=guild;
    }

    @Override
    public void onReply(Message message) {
        if (message.getContentRaw().equalsIgnoreCase("!ku")) {

        }else if (message.getContentRaw().equalsIgnoreCase("!guest")) {
            MessageBuilder mb = new MessageBuilder();
            mb.append("Denne discord server er rettet mod elever på KU. Hvis du alligevel mener du skal have adgang til vores discord server, så bedes du kontakte en Mod");
            mb.appendCodeLine("")
            message.getChannel().sendMessage("Denne discord server er rettet mod elever på KU");
        }
    }

    @Override
    public Message getMessage() {
        MessageBuilder mb = new MessageBuilder();
        mb.append("Hej / Hello / Bonjour! "+user.getAsMention()+", og velkommen til DIKU 2020's Discord Server!");
        mb.append("For at komme igang skal du følge et par af vores instruktioner. Alt du vil vide om serveren kan findes #Welcome");
        mb.appendCodeLine("Instruktioner:");
        mb.appendCodeLine("Skriv !ku - Hvis du går på KU");
        mb.appendCodeLine("Skriv !guest - Hvis du ikke går på KU ");
        mb.buildAll(MessageBuilder.SplitPolicy.NEWLINE);

        return null;
    }
}
