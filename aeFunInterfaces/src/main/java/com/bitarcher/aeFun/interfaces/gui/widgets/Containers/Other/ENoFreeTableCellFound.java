package com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.ITable;

/**
 * Created by michel on 05/03/15.
 */
public class ENoFreeTableCellFound extends RuntimeException {

    ITable table;
    IWidget widget;

    public ITable getTable() {
        return table;
    }

    public IWidget getWidget() {
        return widget;
    }

    public ENoFreeTableCellFound(ITable table, IWidget widget) {
        super("Can not insert more widgets, no free cell found");
        this.table = table;
        this.widget = widget;
    }
}
