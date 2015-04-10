package com.bitarcher.aeFun.interfaces.gui.widgets;

/**
 * Created by michel on 22/01/15.
 */
public interface IWidgetListener {
    void onEnabledChanged(IWidget widget, boolean enabled);
    void onSizeChanged(IWidget widget);
    void onPositionChanged(IWidget widget);
    void onPaddingChanged(IWidget widget);
}
