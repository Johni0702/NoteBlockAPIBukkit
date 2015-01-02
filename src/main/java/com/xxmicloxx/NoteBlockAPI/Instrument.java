package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.Sound;

public enum Instrument {

    PIANO(Sound.NOTE_PIANO, org.bukkit.Instrument.PIANO),
    BASS_GUITAR(Sound.NOTE_BASS_GUITAR, org.bukkit.Instrument.BASS_GUITAR),
    BASS_DRUM(Sound.NOTE_BASS_DRUM, org.bukkit.Instrument.BASS_DRUM),
    SNARE_DRUM(Sound.NOTE_SNARE_DRUM, org.bukkit.Instrument.SNARE_DRUM),
    STICKS(Sound.NOTE_STICKS, org.bukkit.Instrument.STICKS);

    private final Sound sound;
    private final org.bukkit.Instrument bukkitInstrument;

    Instrument(Sound sound, org.bukkit.Instrument bukkitInstrument) {
        this.sound = sound;
        this.bukkitInstrument = bukkitInstrument;
    }

    public Sound getSound() {
        return sound;
    }

    public org.bukkit.Instrument getBukkitInstrument() {
        return bukkitInstrument;
    }

    public static Sound getInstrument(byte instrument) {
        return Instrument.values()[instrument].getSound();
    }

    public static org.bukkit.Instrument getBukkitInstrument(byte instrument) {
        return Instrument.values()[instrument].getBukkitInstrument();
    }
}
