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
public class ETableCellNotEmpty extends RuntimeException {
    ITable table;
    IWidget widget;
    int left, right, columnsSpan, rowSpan;
    int cellColumnNum;
    int cellRowNum;


    public ETableCellNotEmpty(ITable table, IWidget widget, int left, int right, int columnsSpan, int rowSpan, int cellColumnNum, int cellRowNum) {
        super("Table cell already covered");
        this.table = table;

        this.widget = widget;
        this.left = left;
        this.right = right;
        this.columnsSpan = columnsSpan;
        this.rowSpan = rowSpan;
        this.cellColumnNum = cellColumnNum;
        this.cellRowNum = cellRowNum;
    }

    public ITable getTable() {
        return table;
    }


    public IWidget getWidget() {
        return widget;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getColumnsSpan() {
        return columnsSpan;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public int getCellColumnNum() {
        return cellColumnNum;
    }

    public int getCellRowNum() {
        return cellRowNum;
    }
}
