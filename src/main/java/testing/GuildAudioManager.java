package testing;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import discord4j.common.util.Snowflake;
import discord4j.voice.AudioProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static testing.Main.PLAYER_MANAGER;

public final class GuildAudioManager {

    private static final Map<Snowflake, GuildAudioManager> MANAGERS = new ConcurrentHashMap<>();

    public static GuildAudioManager of(final Snowflake id) {
        return MANAGERS.computeIfAbsent(id, ignored -> new GuildAudioManager());
    }

    private final AudioPlayer player;
    private final AudioTrackScheduler scheduler;
    private final LavaPlayerAudioProvider provider;

    private GuildAudioManager() {
        player = PLAYER_MANAGER.createPlayer();
        scheduler = new AudioTrackScheduler(player);
        provider = new LavaPlayerAudioProvider(player);

        player.addListener(scheduler);
    }

    public AudioProvider getProvider() {
        return this.provider;
    }

    // getters
}
