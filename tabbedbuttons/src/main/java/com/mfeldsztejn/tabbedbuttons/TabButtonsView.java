package com.mfeldsztejn.tabbedbuttons;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by mfeldsztejn on 12/20/16.
 */

public class TabButtonsView extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private TextView title;
    private Drawable buttonBackground;
    private Drawable buttonDrawable;
    private int buttonTextColor;
    private TabButtonSelectedCallback tabButtonSelectedCallback;

    public TabButtonsView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.tabbed_buttons_view, this);
        this.radioGroup = (RadioGroup) findViewById(R.id.tabbed_button_view_radio_group);
        this.title = (TextView) findViewById(R.id.tabbed_button_view_title);

        if(attrs == null){
            return;
        }
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.TabButtonsView, 0, 0);

        String title = a.getString(R.styleable.TabButtonsView_groupTitle);
        buttonBackground = a.getDrawable(R.styleable.TabButtonsView_buttonBackground);
        buttonDrawable = a.getDrawable(R.styleable.TabButtonsView_buttonDrawable);
        buttonTextColor = a.getColor(R.styleable.TabButtonsView_buttonTextColor, Color.WHITE);
        int titleTextColor = a.getColor(R.styleable.TabButtonsView_titleTextColor, Color.BLACK);

        setTitle(title);
        setTitleTextColor(titleTextColor);
    }

    public void setTitle(String title) {
        if(TextUtils.isEmpty(title)){
            this.title.setVisibility(GONE);
        } else {
            this.title.setText(title);
        }
    }

    private void setTitleTextColor(int titleTextColor) {
        this.title.setTextColor(titleTextColor);
    }

    public TabButtonsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(11)
    public TabButtonsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public TabButtonsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void setTabButtonSelectedCallback(TabButtonSelectedCallback callback) {
        this.tabButtonSelectedCallback = callback;
    }

    public void setOptions(TabButton... options){
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1f / options.length;
        for (TabButton option : options) {
            radioGroup.addView(getRadioButton(layoutParams, option));
        }
    }

    private RadioButton getRadioButton(RadioGroup.LayoutParams layoutParams, TabButton option) {
        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setLayoutParams(layoutParams);
        radioButton.setTag(option);
        radioButton.setText(option.getText());
        radioButton.setTextColor(buttonTextColor);
        radioButton.setButtonDrawable(buttonDrawable);
        setRadioButtonBackground(radioButton);
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setOnCheckedChangeListener(this);
        return radioButton;
    }

    private void setRadioButtonBackground(RadioButton radioButton){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            radioButton.setBackground(buttonBackground);
        } else {
            radioButton.setBackgroundDrawable(buttonBackground);
        }
    }

    public void setDrawables(@Nullable Drawable buttonBackground, @Nullable Drawable buttonDrawable) {
        this.buttonBackground = buttonBackground;
        this.buttonDrawable = buttonDrawable;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton childAt = (RadioButton) radioGroup.getChildAt(i);
            setRadioButtonBackground(childAt);
            childAt.setButtonDrawable(buttonDrawable);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (tabButtonSelectedCallback != null && isChecked) {
            tabButtonSelectedCallback.onTabButtonSelected((TabButton) buttonView.getTag());
        }
    }
}
