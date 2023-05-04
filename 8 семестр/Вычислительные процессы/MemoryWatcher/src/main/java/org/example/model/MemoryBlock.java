package org.example.model;

//Класс, содержащий информацию о блоке памяти
public class MemoryBlock {
    public String address;
    public long kbytes;
    public long rss;
    public long dirty;
    public String mode;
    public String mapping;

    public MemoryBlock(String address, long kbytes, long rss, long dirty, String mode, String mapping) {
        this.address = address;
        this.kbytes = kbytes;
        this.rss = rss;
        this.dirty = dirty;
        this.mode = mode;
        this.mapping = mapping;
    }

    public String getAddress() {
        return address;
    }

    public long getKbytes() {
        return kbytes;
    }

    public long getRss() {
        return rss;
    }

    public long getDirty() {
        return dirty;
    }

    public String getMode() {
        return mode;
    }

    public String getMapping() {
        return mapping;
    }
}
