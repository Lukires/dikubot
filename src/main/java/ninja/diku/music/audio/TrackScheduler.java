package ninja.diku.music.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.Guild;
import ninja.diku.main.Util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {
    
    private final Queue<AudioTrack> queue = new LinkedBlockingQueue<AudioTrack>();
    private final AudioContext context;
    public TrackScheduler(AudioContext context) {
        this.context=context;
    }

    public void queue(AudioPlayer player, AudioTrack track) {
        if(player.getPlayingTrack() == null) {
            player.playTrack(track);
            return;
        }
        for (AudioTrack exists : getQueue()) {
            if(exists.getIdentifier().equals(track.getIdentifier())) {
                context.getMessageChannel().sendMessage(":x: " + track.getInfo().title + " er allerede i køen").queue();
                return;
            }
        }

        if(queue.size() > 1000) {
            context.getMessageChannel().sendMessage(":x: Der er over tusinde sange i køen, lad os tage det lidt med ro").queue();
            return;
        }

        queue.add(track);
        String time = Util.milisecondsToMinuteString(track.getDuration());
        context.getMessageChannel().sendMessage(":alarm_clock: Tilføjet til køen: " + track.getInfo().title + " **Længde: " + time + "** min").queue();
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        if (context.getVoiceChannel().getMembers().size() == 0) {
            context.getMessageChannel().sendMessage(":musical_note: Der er ingen til stede, stopper med at spille musik...").queue();
            player.stopTrack();
            leaveVoiceChannel();
            return;
        }
        joinVoiceChannel();
        String time = Util.milisecondsToMinuteString(track.getDuration());
        context.getMessageChannel().sendMessage(":musical_note: Spiller nu: " + track.getInfo().title + " **Længde: " + time + "** min").queue();
    }

    // endReason == FINISHED: A track finished or died by an exception (mayStartNext = true).
    // endReason == LOAD_FAILED: Loading of a track failed (mayStartNext = true).
    // endReason == STOPPED: The player was stopped.
    // endReason == REPLACED: Another track started playing while this had not finished
    // endReason == CLEANUP: Player hasn't been queried for a while, if you want you can put a
    //                       clone of this back to your queue

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if ((!endReason.equals(AudioTrackEndReason.STOPPED) && !endReason.mayStartNext) || queue.isEmpty()) {
            clearQueue();
            leaveVoiceChannel();
            return;
        }
        player.playTrack(queue.poll());

    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
        context.getMessageChannel().sendMessage(":x: Fejl, kan ikke spille " + track.getInfo().title).queue();
        player.stopTrack();
    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
        if (thresholdMs > 5000) {
            context.getMessageChannel().sendMessage(":x: Fejl, kan ikke spille " + track.getInfo().title).queue();
            player.stopTrack();
        }
    }

    public void joinVoiceChannel() {
        context.getGuild().getAudioManager().openAudioConnection(context.getVoiceChannel());
    }
    public void leaveVoiceChannel() {
        context.getGuild().getAudioManager().closeAudioConnection();
    }

    public void clearQueue() {
        queue.clear();
    }

    public ArrayList<AudioTrack> getQueue() {
        return new ArrayList<>(queue);
    }
}
