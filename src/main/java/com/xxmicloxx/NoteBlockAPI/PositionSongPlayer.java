package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PositionSongPlayer extends SongPlayer {

    private Location targetLocation;

    public PositionSongPlayer(Song song) {
        super(song);
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    @Override
    public void playTick(Player p, int tick) {
        if (!p.getWorld().equals(targetLocation.getWorld())) {
            // not in same world
            return;
        }
        byte playerVolume = NoteBlockPlayerMain.getPlayerVolume(p);

        for (Layer l : song.getLayerMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            Instrument instrument = note.getInstrument();
            float volume = l.getVolume() * this.volume * playerVolume / 1000000f;
            p.playSound(targetLocation, instrument.getSound(), volume, NotePitch.getPitch(note.getKey() - 33));
        }
    }
}
