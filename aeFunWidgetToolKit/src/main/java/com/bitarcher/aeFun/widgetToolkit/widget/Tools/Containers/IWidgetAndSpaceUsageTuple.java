package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ISpaceUsage;

interface IWidgetAndSpaceUsageTuple {
    IWidget getWidget();
    ISpaceUsage getSpaceUsage();
}
