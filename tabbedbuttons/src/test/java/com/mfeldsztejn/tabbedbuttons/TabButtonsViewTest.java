package com.mfeldsztejn.tabbedbuttons;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.junit.Test;
import org.robolectric.util.ReflectionHelpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * Created by mfeldsztejn on 12/22/16.
 */
public class TabButtonsViewTest extends RobolectricTest {

    @Test
    public void testConstructor_withJustContext_shouldOnlyCreateViewWithoutConfigurations() throws Exception {
        TabButtonsView tabButtonsView = new TabButtonsView(getContext());

        TextView titleTextView = ReflectionHelpers.getField(tabButtonsView, "title");
        assertEquals("", titleTextView.getText());

        RadioGroup radioGroup = ReflectionHelpers.getField(tabButtonsView, "radioGroup");
        assertEquals(0, radioGroup.getChildCount(), 0);

        Drawable buttonBackground = ReflectionHelpers.getField(tabButtonsView, "buttonBackground");
        Drawable buttonDrawable = ReflectionHelpers.getField(tabButtonsView, "buttonDrawable");
        int buttonTextColor = ReflectionHelpers.getField(tabButtonsView, "buttonTextColor");

        assertNull("Value should be null since it wasn't initialized", buttonBackground);
        assertNull("Value should be null since it wasn't initialized", buttonDrawable);
        assertEquals("Value should be 0 since it wasn't initialized", 0, buttonTextColor);
    }

    @Test
    public void testConstructor_withContextAndAttributeSet_shouldInitAndConfigureView() throws Exception {
        Context context = spy(getContext());
//        Resources.Theme theme = mock(Resources.Theme.class);
        TypedArray typedArray = mock(TypedArray.class);

        doReturn(typedArray).when(context).obtainStyledAttributes(any(AttributeSet.class), any(int[].class), anyInt(), anyInt());
//        doReturn(theme).when(context).getTheme();

        ColorDrawable aColorDrawable = new ColorDrawable(Color.parseColor("#A4C1F9"));
        doReturn(Color.parseColor("#A4C1F9")).when(typedArray).getColor(eq(R.styleable.TabButtonsView_buttonTextColor), anyInt());
        doReturn(aColorDrawable).when(typedArray).getDrawable(R.styleable.TabButtonsView_buttonBackground);
        doReturn(aColorDrawable).when(typedArray).getDrawable(R.styleable.TabButtonsView_buttonDrawable);
        doReturn("Group title").when(typedArray).getString(R.styleable.TabButtonsView_groupTitle);
        doReturn(Color.parseColor("#F1A8C2")).when(typedArray).getColor(eq(R.styleable.TabButtonsView_titleTextColor), anyInt());

        TabButtonsView tabButtonsView = new TabButtonsView(context, mock(AttributeSet.class));

        TextView titleTextView = ReflectionHelpers.getField(tabButtonsView, "title");
        assertEquals("Group title", titleTextView.getText());
        assertEquals(ColorStateList.valueOf(Color.parseColor("#F1A8C2")), titleTextView.getTextColors());

        RadioGroup radioGroup = ReflectionHelpers.getField(tabButtonsView, "radioGroup");
        assertEquals("This should be zero since no options have been added", 0, radioGroup.getChildCount(), 0);

        Drawable buttonBackground = ReflectionHelpers.getField(tabButtonsView, "buttonBackground");
        Drawable buttonDrawable = ReflectionHelpers.getField(tabButtonsView, "buttonDrawable");
        int buttonTextColor = ReflectionHelpers.getField(tabButtonsView, "buttonTextColor");
        assertEquals(aColorDrawable, buttonBackground);
        assertEquals(aColorDrawable, buttonDrawable);
        assertEquals(Color.parseColor("#A4C1F9"), buttonTextColor);
    }

}