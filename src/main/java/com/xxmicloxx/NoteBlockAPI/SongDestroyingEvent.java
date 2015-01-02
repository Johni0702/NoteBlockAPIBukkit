package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class SongDestroyingEvent extends SongPlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private boolean cancelled = false;

    public SongDestroyingEvent(SongPlayer songPlayer) {
        super(songPlayer);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
