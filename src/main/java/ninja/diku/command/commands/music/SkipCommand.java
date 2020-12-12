package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class SkipCommand implements MusicCommand {
    @Override
    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, Message message) {
        AudioPlayer player = audioManager.getPlayer(new AudioContext(guild, voiceChannel, messageChannel));
        player.stopTrack();
    }

    @Override
    public String getDescription() {
        return "Spring musik over";
    }

    @Override
    public String getUsage() {
        return "!skip";
    }
}
