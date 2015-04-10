package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.ForTable;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.IPositionable;
import com.bitarcher.aeFun.interfaces.geometry.ISizable;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.IWidgetTableCellsConsumption;

/**
 * Created by michel on 04/03/15.
 */
public interface ITableCell extends IPositionable, ISizable {
    int getColumnNum();
    int getRowNum();
    ITableColumn getColumn();
    ITableRow getRow();

    // returns null if there is none actually
    IWidgetTableCellsConsumption getWidgetTableCellsConsumption();
    void setWidgetTableCellsConsumption(IWidgetTableCellsConsumption widgetTableCellsConsumption);
}
