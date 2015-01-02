package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: ml
 * Date: 07.12.13
 * Time: 12:56
 */
public class NoteBlockSongPlayer extends SongPlayer {
    private Block noteBlock;

    public NoteBlockSongPlayer(Song song) {
        super(song);
    }

    public Block getNoteBlock() {
        return noteBlock;
    }

    public void setNoteBlock(Block noteBlock) {
        this.noteBlock = noteBlock;
    }

    @Override
    public void playTick(Player p, int tick) {
        if (noteBlock.getType() != Material.NOTE_BLOCK) {
            return;
        }
        if (!p.getWorld().equals(noteBlock.getWorld())) {
            // not in same world
            return;
        }
        Location loc = noteBlock.getLocation();
        byte playerVolume = NoteBlockPlayerMain.getPlayerVolume(p);

        for (Layer l : song.getLayerMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            Instrument instrument = note.getInstrument();
            float volume = l.getVolume() * this.volume * playerVolume / 1000000f;
            p.playNote(loc, instrument.getBukkitInstrument(), new org.bukkit.Note(note.getKey() - 33));
            p.playSound(loc, instrument.getSound(), volume, NotePitch.getPitch(note.getKey() - 33));
        }
    }
}
