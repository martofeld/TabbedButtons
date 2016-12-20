package com.mfeldsztejn.tabbedbuttons;

/**
 * Created by mfeldsztejn on 12/20/16.
 */

public class TabButton {
    private String text;
    private Object tag;

    public TabButton(String text, Object tag) {
        this.text = text;
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public <T> T getTag() {
        return (T) tag;
    }

}
