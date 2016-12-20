package com.mfeldsztejn.tabbedbuttons;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by mfeldsztejn on 12/20/16.
 */

public class TabbedButtonsView extends LinearLayout {
    public TabbedButtonsView(Context context) {
        super(context);
    }

    public TabbedButtonsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(11)
    public TabbedButtonsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public TabbedButtonsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
