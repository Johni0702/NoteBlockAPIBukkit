package com.xxmicloxx.NoteBlockAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public abstract class SongPlayer {

    protected final Song song;
    protected boolean playing = false;
    protected short tick = -1;
    protected Set<Player> players = Collections.newSetFromMap(new WeakHashMap<Player, Boolean>());
    protected boolean autoDestroy = false;
    protected boolean destroyed = false;
    protected byte fadeTarget = 100;
    protected byte volume = 100;
    protected byte fadeStart = volume;
    protected int fadeDuration = 60;
    protected int fadeDone = 0;
    protected FadeType fadeType = new LinearFading();
    private final Timer timer;

    public SongPlayer(Song song) {
        this.song = song;
        timer = new Timer();
    }

    public FadeType getFadeType() {
        return fadeType;
    }

    public void setFadeType(FadeType fadeType) {
        this.fadeType = fadeType;
    }

    public byte getFadeTarget() {
        return fadeTarget;
    }

    public void setFadeTarget(byte fadeTarget) {
        this.fadeTarget = fadeTarget;
    }

    public byte getFadeStart() {
        return fadeStart;
    }

    public void setFadeStart(byte fadeStart) {
        this.fadeStart = fadeStart;
    }

    public int getFadeDuration() {
        return fadeDuration;
    }

    public void setFadeDuration(int fadeDuration) {
        this.fadeDuration = fadeDuration;
    }

    public int isFadeDone() {
        return fadeDone;
    }

    public void setFadeDone(int fadeDone) {
        this.fadeDone = fadeDone;
    }

    protected void calculateFade() {
        if (fadeDone == fadeDuration) {
            return; // no fade today
        }
        double targetVolume = fadeType.calculateVolume(fadeDone, fadeDuration, fadeStart, fadeTarget);
        setVolume((byte) targetVolume);
        fadeDone++;
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public void addPlayer(Player p) {
        if (players.add(p)) {
            List<SongPlayer> songs = NoteBlockPlayerMain.plugin.playingSongs.get(p);
            if (songs == null) {
                songs = new ArrayList<>();
                NoteBlockPlayerMain.plugin.playingSongs.put(p, songs);
            }
            songs.add(this);
        }
    }

    public boolean getAutoDestroy() {
        return autoDestroy;
    }

    public void setAutoDestroy(boolean autoDestroy) {
        this.autoDestroy = autoDestroy;
    }

    public abstract void playTick(Player p, int tick);

    public void destroy() {
        SongDestroyingEvent event = new SongDestroyingEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        timer.cancel();
        destroyed = true;
        playing = false;
        setTick((short) -1);
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        if (this.playing == playing) return;
        this.playing = playing;
        if (playing) {
            timer.runTaskTimer(NoteBlockPlayerMain.plugin, 0, (int) song.getDelay());
        } else {
            Bukkit.getPluginManager().callEvent(new SongStoppedEvent(this));
        }
    }

    public short getTick() {
        return tick;
    }

    public void setTick(short tick) {
        this.tick = tick;
    }

    public void removePlayer(Player p) {
        if (!players.remove(p)) {
            return;
        }
        List<SongPlayer> songs = NoteBlockPlayerMain.plugin.playingSongs.get(p);
        songs.remove(this);
        if (songs.isEmpty()) {
            NoteBlockPlayerMain.plugin.playingSongs.remove(p);
        }
        if (players.isEmpty() && autoDestroy) {
            Bukkit.getPluginManager().callEvent(new SongEndEvent(this));
            destroy();
        }
    }

    public byte getVolume() {
        return volume;
    }

    public void setVolume(byte volume) {
        this.volume = volume;
    }

    public Song getSong() {
        return song;
    }

    private class Timer extends BukkitRunnable {
        @Override
        public void run() {
            if (destroyed) {
                cancel();
                return;
            }
            if (playing) {
                calculateFade();
                tick++;
                if (tick > song.getLength()) {
                    playing = false;
                    tick = -1;
                    Bukkit.getPluginManager().callEvent(new SongEndEvent(SongPlayer.this));
                    if (autoDestroy) {
                        destroy();
                        return;
                    }
                }
                for (Player player : players) {
                    playTick(player, tick);
                }
            }
        }
    }
}
