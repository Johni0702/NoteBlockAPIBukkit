package com.xxmicloxx.NoteBlockAPI;

import java.util.HashMap;
import java.util.Map;

public class Layer {

    private Map<Integer, Note> map = new HashMap<>();
    private byte volume = 100;
    private String name = "";

    @Deprecated
    @SuppressWarnings("unchecked")
    public HashMap<Integer, Note> getHashMap() {
        return (HashMap) map;
    }

    @Deprecated
    public void setHashMap(HashMap<Integer, Note> hashMap) {
        this.map = hashMap;
    }

    public Map<Integer, Note> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Note> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Note getNote(int tick) {
        return map.get(tick);
    }

    public void setNote(int tick, Note note) {
        map.put(tick, note);
    }

    public byte getVolume() {
        return volume;
    }

    public void setVolume(byte volume) {
        this.volume = volume;
    }
}
