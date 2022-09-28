package com.hm;

public class WxapkgItem {
    private String name;
    private int start;
    private int length;
    public WxapkgItem(String name, int start, int length) {
        this.name = name;
        this.start = start;
        this.length = length;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    @Override
    public String toString() {
        return "com.hm.WxapkgItem{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", length=" + length +
                '}';
    }
}