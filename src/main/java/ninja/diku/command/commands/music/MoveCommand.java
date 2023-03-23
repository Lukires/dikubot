package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import ninja.diku.command.Command;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class MoveCommand implements DJCommand {
    @Override
    public void onCommand(Member member, Guild guild, MessageChannelUnion messageChannel, AudioChannelUnion voiceChannel, AudioPlayer player, Message message) {
        if(player.getPlayingTrack() == null) {
            messageChannel.sendMessage(":x: Du kan kun flytte mig når jeg spiller musik").queue();
            return;
        }

        MessageChannelUnion currentMessageChannel = player.getContext().getMessageChannel();
        AudioChannelUnion currentVoiceChannel = player.getContext().getVoiceChannel();
        player.getContext().setMessageChannel(messageChannel);
        player.getContext().setVoiceChannel(voiceChannel);

        if (!player.getScheduler().joinVoiceChannel()) {
            messageChannel.sendMessage(":x: Fejl, jeg har ikke adgang til at spille i din kanal. Jeg bliver hvor jeg er!").queue();
            player.getContext().setMessageChannel(currentMessageChannel);
            player.getContext().setVoiceChannel(currentVoiceChannel);
            return;
        }
        messageChannel.sendMessage(":airplane: Du har flyttet mig til **voice channel: " + voiceChannel.getName() + "** og **message channel: " + messageChannel.getName()+ "**").queue();
    }

    @Override
    public String getDescription() {
        return "Flyt musik botten til den kanal du er i";
    }

    @Override
    public String getUsage() {
        return "!move";
    }
}
