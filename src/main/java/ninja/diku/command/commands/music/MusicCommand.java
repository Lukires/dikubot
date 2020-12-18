package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import ninja.diku.command.Command;
import ninja.diku.models.UserModel;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioManager;
import ninja.diku.music.audio.AudioPlayer;

public interface MusicCommand extends Command {

    AudioManager audioManager = AudioManager.getInstance();

    /**
     *
     * @param  member   The member object.
     * @param  guild    The guild object, the user belongs to.
     * @param  messageChannel  The channel object, the message was written in.
     * @param  voiceChannel  The voice channel object, which the user is in.
     * @param  player        The audio player object, which is used to control the music in the given Guild.
     * @param  message  The message object, the user wrote.
     * @return      void
     * @see MusicCommand
     * @see Command
     */
    void onCommand(Member member, Guild guild, MessageChannel messageChannel, VoiceChannel voiceChannel, AudioPlayer player, Message message);


    @Override
    default void onCommand(User user, Guild guild, MessageChannel channel, Message message) {
        Member member = guild.retrieveMember(user).complete();

        UserModel userModel = new UserModel(user);
        if (userModel.isMusicBanned()) {
            channel.sendMessage("Du kan ikke spille musik. Din musiksmag har åbenbart fået dig banned.").queue();
            return;
        }

        GuildVoiceState voiceState = member.getVoiceState();
        assert voiceState != null;
        if(!voiceState.inVoiceChannel()) {
            channel.sendMessage("Jeg kan ikke spille musik for dig hvis du ikke er i en voice channel :(").queue();
            return;
        }

        if(!audioManager.playerExists(guild)) {
            audioManager.createPlayer(new AudioContext(guild, voiceState.getChannel(), channel));
        }
        onCommand(member, guild, channel, voiceState.getChannel(), audioManager.getPlayer(guild), message);
    }
}
