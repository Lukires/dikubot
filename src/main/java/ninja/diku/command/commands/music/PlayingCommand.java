package ninja.diku.command.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import ninja.diku.main.Constant;
import ninja.diku.main.Util;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class PlayingCommand implements MusicCommand {
    @Override
    public void onCommand(Member member, Guild guild, MessageChannelUnion messageChannel, AudioChannelUnion voiceChannel, AudioPlayer player, Message message) {
        AudioTrack playing = player.getPlayingTrack();
        if (playing==null) {
           messageChannel.sendMessage(":x: Der bliver ikke spillet noget lige nu").queue();
           return;
        }
        messageChannel.sendMessage(":musical_note: Spiller lige nu: " + playing.getInfo().title + " - **Længde: " +
                Util.milisecondsToMinuteString(playing.getDuration()) + " / " + Util.milisecondsToMinuteString(playing.getPosition()) + "** min").queue();
    }

    @Override
    public String getDescription() {
        return "Se hvilken sang der spiller";
    }

    @Override
    public String getUsage() {
        return "!playing";
    }
}
