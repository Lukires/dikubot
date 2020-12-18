package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.command.Command;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class MoveCommand implements MusicCommand {
    @Override
    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, AudioPlayer player, Message message) {
        player.getContext().setMessageChannel(messageChannel);
        player.getContext().setVoiceChannel(voiceChannel);
        player.getScheduler().joinVoiceChannel();
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
