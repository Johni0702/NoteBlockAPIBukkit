package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class NoteBlockPlayerMain extends JavaPlugin {

    public static NoteBlockPlayerMain plugin;
    public Map<Player, List<SongPlayer>> playingSongs = new WeakHashMap<>();
    public Map<Player, Byte> playerVolume = new WeakHashMap<>();

    public static boolean isReceivingSong(Player p) {
        List<SongPlayer> songs = plugin.playingSongs.get(p);
        return songs != null && !songs.isEmpty();
    }

    public static void stopPlaying(Player p) {
        List<SongPlayer> songs = plugin.playingSongs.get(p);
        if (songs == null) {
            return;
        }
        for (SongPlayer s : songs) {
            s.removePlayer(p);
        }
    }

    public static void setPlayerVolume(Player p, byte volume) {
        plugin.playerVolume.put(p, volume);
    }

    public static byte getPlayerVolume(Player p) {
        Byte b = plugin.playerVolume.get(p);
        return b == null ? 100 : b;
    }

    @Override
    public void onEnable() {
        plugin = this;
    }
}
