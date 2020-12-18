package ninja.diku.command.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.*;
import ninja.diku.command.Command;
import ninja.diku.main.Constant;
import ninja.diku.main.Util;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioPlayer;

public class PlayingCommand implements MusicCommand {

    /**
     * Prints what is currently being played
     *
     * @param  member   The member object.
     * @param  guild    The guild object, the user belongs to.
     * @param  messageChannel  The channel object, the message was written in.
     * @param  voiceChannel  The voice channel object, which the user is in.
     * @param  player        The audio player object, which is used to control the music in the given Guild.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see         MusicCommand
     * @see Command
     */
    @Override
    public void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, AudioPlayer player, Message message) {
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
