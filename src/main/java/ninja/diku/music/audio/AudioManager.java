package ninja.diku.music.audio;

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;

public class AudioManager extends DefaultAudioPlayerManager {

    private static final HashMap<Guild, AudioPlayer> players = new HashMap<Guild, AudioPlayer>();
    private static final AudioManager instance = new AudioManager();
    private AudioManager() {
        AudioSourceManagers.registerRemoteSources(this);
    }

    public static AudioManager getInstance() {
        return instance;
    }

    public AudioPlayer createPlayer(AudioContext context) {
        Guild guild = context.getGuild();
        AudioPlayer player = new AudioPlayer(this, context);
        guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));
        players.put(guild, player);
        return player;
    }

    public AudioPlayer getPlayer(AudioContext context) {
        Guild guild = context.getGuild();
        if (players.containsKey(guild)) {
            return players.get(guild);
        }
        return createPlayer(context);
    }

    public void removePlayer(Guild guild) {
        players.remove(guild);
    }
}
