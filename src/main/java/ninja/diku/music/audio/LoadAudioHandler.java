package ninja.diku.music.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class LoadAudioHandler implements AudioLoadResultHandler {

    private AudioPlayer player;
    public LoadAudioHandler(AudioPlayer player) {
        this.player=player;
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        player.getScheduler().queue(player, track, true);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        if (playlist.isSearchResult()) {
            trackLoaded(playlist.getTracks().get(0));
            return;
        }

        int added = 0;
        for (AudioTrack track : playlist.getTracks()) {
            if (player.getScheduler().queue(player, track, false)) {
                added++;
            }
        }
        if (added == playlist.getTracks().size()) {
            player.getContext().getMessageChannel().sendMessage(":alarm_clock: Alle **" + added + "** sange er blevet tilføjet").queue();
            return;
        }
        player.getContext().getMessageChannel().sendMessage(":timer: Kun **" + added + "/" + playlist.getTracks().size() + "** af sangene er blevet tilføjet").queue();
    }

    @Override
    public void noMatches() {
        // Notify the user that we've got nothing
        player.getContext().getMessageChannel().sendMessage(":x: Kunne ikke finde noget...").queue();
    }

    @Override
    public void loadFailed(FriendlyException throwable) {
        // Notify the user that everything exploded
        player.getContext().getMessageChannel().sendMessage(":boom: Et eller andet stoppede med at virke!").queue();
    }
}
