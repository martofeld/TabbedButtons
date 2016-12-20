package com.mfeldsztejn.tabbedbuttons;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by mfeldsztejn on 12/20/16.
 */

public class TabbedButtonsView extends LinearLayout {
    private RadioGroup radioGroup;
    private TextView title;

    public TabbedButtonsView(Context context) {
        super(context);
        init(context, null);
    }

    public TabbedButtonsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(11)
    public TabbedButtonsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public TabbedButtonsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.tabbed_buttons_view, this);
        if(attrs == null){
            return;
        }
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.TabbedButtonsView, 0, 0);

        this.radioGroup = (RadioGroup) findViewById(R.id.tabbed_button_view_radio_group);
        this.title = (TextView) findViewById(R.id.tabbed_button_view_title);

        String title = a.getString(R.styleable.TabbedButtonsView_groupTitle);

        if (TextUtils.isEmpty(title)) {
            this.title.setVisibility(GONE);
        } else {
            this.title.setText(title);
        }
    }
}
