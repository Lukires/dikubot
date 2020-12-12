package ninja.diku.music.audio;

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;

public class AudioPlayer extends DefaultAudioPlayer {

    private final TrackScheduler scheduler;
    private final AudioContext context;
    public AudioPlayer(DefaultAudioPlayerManager manager, AudioContext context) {
        super(manager);
        this.scheduler=new TrackScheduler(context);
        this.addListener(scheduler);
        this.context=context;
    }

    public TrackScheduler getScheduler() {
        return scheduler;
    }

    public AudioContext getContext() {
        return context;
    }
}
