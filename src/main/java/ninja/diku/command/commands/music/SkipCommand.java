package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class SkipCommand implements MusicCommand {
    @Override
    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, AudioPlayer player, Message message) {
        messageChannel.sendMessage(":arrow_right_hook: Skipping " + player.getPlayingTrack().getInfo().title).queue();
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
