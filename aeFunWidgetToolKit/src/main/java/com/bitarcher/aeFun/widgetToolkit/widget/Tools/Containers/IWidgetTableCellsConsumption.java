package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;

/**
 * Created by michel on 04/03/15.
 */
public interface IWidgetTableCellsConsumption {
    IWidget getWidget();
    int getLeft();
    int getTop();
    int getColumnSpan();
    int getRowSpan();
}