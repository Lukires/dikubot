package ninja.diku.music.audio;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class AudioContext {
    private Guild guild;
    private boolean DJMode = false;
    private AudioChannelUnion voiceChannel;
    private MessageChannelUnion messageChannel;
    public AudioContext(Guild guild, AudioChannelUnion voiceChannel, MessageChannelUnion messageChannel) {
        this.guild=guild;
        this.voiceChannel=voiceChannel;
        this.messageChannel=messageChannel;
    }

    public Guild getGuild() {
        return guild;
    }

    public AudioChannelUnion getVoiceChannel() {
        return voiceChannel;
    }

    public boolean isDjMode() {
        return DJMode;
    }

    public void setDjMode(boolean djMode) {
        this.DJMode = djMode;
    }

    public void setVoiceChannel(AudioChannelUnion voiceChannel) {
        this.voiceChannel = voiceChannel;
    }

    public MessageChannelUnion getMessageChannel() {
        return messageChannel;
    }

    public void setMessageChannel(MessageChannelUnion messageChannel) {
        this.messageChannel = messageChannel;
    }
}
