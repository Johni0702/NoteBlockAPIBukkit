package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.event.Event;

public abstract  class SongPlayerEvent extends Event {
    private final SongPlayer songPlayer;

    public SongPlayerEvent(SongPlayer songPlayer) {
        this.songPlayer = songPlayer;
    }

    public SongPlayer getSongPlayer() {
        return songPlayer;
    }
}
