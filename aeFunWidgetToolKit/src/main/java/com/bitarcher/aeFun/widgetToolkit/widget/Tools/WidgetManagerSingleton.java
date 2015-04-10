package com.bitarcher.aeFun.widgetToolkit.widget.Tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;

import java.util.ArrayList;

/**
 * Created by michel on 28/02/15.
 */
public class WidgetManagerSingleton {
    boolean debugModeEnabled = false;

    ArrayList<IWidget> widgetArrayList = new ArrayList<>();
    private static WidgetManagerSingleton ourInstance = new WidgetManagerSingleton();

    public static WidgetManagerSingleton getInstance() {
        return ourInstance;
    }

    private WidgetManagerSingleton() {
    }

    public void refWidget(IWidget widget)
    {
        this.widgetArrayList.add(widget);
    }

    public void unRefWidget(IWidget widget)
    {
        this.widgetArrayList.add(widget);
    }

    public boolean isDebugModeEnabled() {
        return debugModeEnabled;
    }

    public void setDebugModeEnabled(boolean debugModeEnabled) {
        this.debugModeEnabled = debugModeEnabled;
    }
}

