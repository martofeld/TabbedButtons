package com.mfeldsztejn.tabbedbuttons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mfeldsztejn on 12/21/16.
 */
public class TabButtonTest {

    private static final String BUTTON_TEXT = "Button text";

    @Test
    public void testConstructor_withTextAndTag_shouldBuildCorrectly() throws Exception {
        TabButtonTag tabButtonTag = new TabButtonTag("A piece of data", "Another piece of data");

        TabButton tabButton = new TabButton(BUTTON_TEXT, tabButtonTag);

        assertEquals(BUTTON_TEXT, tabButton.getText());
        TabButtonTag tabButtonTag1 = tabButton.getTag();
        assertEquals(tabButtonTag, tabButtonTag1);
        assertEquals(tabButtonTag.data1, tabButtonTag1.data1);
        assertEquals(tabButtonTag.data2, tabButtonTag1.data2);
    }

    @Test
    public void testToString_withAllFields_shouldPrintOutClassState() throws Exception {
        TabButtonTag tabButtonTag = new TabButtonTag("A piece of data", "Another piece of data");

        TabButton tabButton = new TabButton(BUTTON_TEXT, tabButtonTag);

        assertEquals("TabButton{text='Button text', tag=TabButtonTag{data1='A piece of data', data2='Another piece of data'}}", tabButton.toString());
    }

    private static class TabButtonTag {
        String data1;
        String data2;

        TabButtonTag(String data1, String data2) {
            this.data1 = data1;
            this.data2 = data2;
        }

        @Override
        public String toString() {
            return "TabButtonTag{" +
                    "data1='" + data1 + '\'' +
                    ", data2='" + data2 + '\'' +
                    '}';
        }
    }

}