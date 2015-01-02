package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.event.HandlerList;

public class SongStoppedEvent extends SongPlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public SongStoppedEvent(SongPlayer songPlayer) {
        super(songPlayer);
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}
