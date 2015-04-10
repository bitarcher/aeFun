package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.ForTable;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.IWidgetTableCellsConsumption;

/**
 * Created by michel on 04/03/15.
 */
public class TableCell implements ITableCell{
    int rowNum;
    int columnNum;
    float x, y, width, height;
    IWidgetTableCellsConsumption widgetTableCellsConsumption = null;
    ITableColumn column;
    ITableRow row;

    @Override
    public IWidgetTableCellsConsumption getWidgetTableCellsConsumption() {
        return widgetTableCellsConsumption;
    }

    @Override
    public void setWidgetTableCellsConsumption(IWidgetTableCellsConsumption widgetTableCellsConsumption) {
        this.widgetTableCellsConsumption = widgetTableCellsConsumption;
    }

    @Override
    public int getRowNum() {
        return rowNum;
    }

    @Override
    public int getColumnNum() {
        return columnNum;
    }


    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public TableCell(int columnNum, int rowNum, ITableColumn column, ITableRow row) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.column = column;
        this.row = row;
    }

    @Override
    public ITableColumn getColumn() {
        return column;
    }

    @Override
    public ITableRow getRow() {
        return row;
    }
}
