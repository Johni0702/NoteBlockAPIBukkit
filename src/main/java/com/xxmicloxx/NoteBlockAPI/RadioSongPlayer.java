package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.entity.Player;

public class RadioSongPlayer extends SongPlayer {

    public RadioSongPlayer(Song song) {
        super(song);
    }

    @Override
    public void playTick(Player p, int tick) {
        byte playerVolume = NoteBlockPlayerMain.getPlayerVolume(p);

        for (Layer l : song.getLayerMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            Instrument instrument = note.getInstrument();
            float volume = l.getVolume() * this.volume * playerVolume / 1000000f;
            p.playSound(p.getEyeLocation(), instrument.getSound(), volume, NotePitch.getPitch(note.getKey() - 33));
        }
    }
}
