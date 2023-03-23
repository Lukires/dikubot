package ninja.diku.command.commands.music;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import ninja.diku.music.audio.AudioPlayer;

public class PlayTopCommand implements DJCommand{
    @Override
    public void onCommand(Member member, Guild guild, MessageChannelUnion messageChannel, AudioChannelUnion voiceChannel, AudioPlayer player, Message message) {
        new PlayCommand().onCommand(member, guild, messageChannel, voiceChannel, player, message, true);
    }

    @Override
    public String getDescription() {
        return "Tilføj musik til toppen af køen";
    }

    @Override
    public String getUsage() {
        return "!playtop";
    }
}
