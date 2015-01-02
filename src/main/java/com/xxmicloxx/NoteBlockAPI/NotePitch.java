package com.xxmicloxx.NoteBlockAPI;

public final class NotePitch {

    private static final float[] PITCH = {
            0.5F, 0.53F, 0.56F, 0.6F, 0.63F, 0.67F, 0.7F, 0.76F, 0.8F, 0.84F,
            0.9F, 0.94F, 1.0F, 1.06F, 1.12F, 1.18F, 1.26F, 1.34F, 1.42F,
            1.5F, 1.6F, 1.68F, 1.78F, 1.88F, 2.0F
    };

    public static float getPitch(int note) {
        return note < PITCH.length ? PITCH[note] : 0;
    }

    private NotePitch() {}
}