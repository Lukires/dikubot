package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import ninja.diku.command.Command;
import ninja.diku.main.Util;
import ninja.diku.models.UserModel;
import ninja.diku.music.audio.AudioContext;
import ninja.diku.music.audio.AudioManager;
import ninja.diku.music.audio.AudioPlayer;

public interface MusicCommand extends Command {

    AudioManager audioManager = AudioManager.getInstance();

    void onCommand(Member member, Guild guild, MessageChannelUnion messageChannel, AudioChannelUnion voiceChannel, AudioPlayer player, Message message);

    @Override
    default void onCommand(User user, Guild guild, MessageChannelUnion channel, Message message) {
        Member member = guild.retrieveMember(user).complete();

        UserModel userModel = new UserModel(user);
        if (userModel.isMusicBanned()) {
            channel.sendMessage(":x: Du kan ikke spille musik. Din musiksmag har åbenbart fået dig banned.").queue();
            return;
        }

        GuildVoiceState voiceState = member.getVoiceState();
        assert voiceState != null;
        if(!voiceState.inAudioChannel()) {
            channel.sendMessage(":x: Jeg kan ikke spille musik for dig hvis du ikke er i en voice channel :(").queue();
            return;
        }

        if(!audioManager.playerExists(guild)) {
            audioManager.createPlayer(new AudioContext(guild, voiceState.getChannel(), channel));
        }

        AudioPlayer player = audioManager.getPlayer(guild);


        if (this instanceof DJCommand && player.getContext().isDjMode() && !DJCommand.isDJ(member, player)) {
            channel.sendMessage(":x: Jeg er i DJmode. Kun DJs kan bruge denne kommando.").queue();
            return;
        }

        onCommand(member, guild, channel, voiceState.getChannel(), player, message);
    }
}

