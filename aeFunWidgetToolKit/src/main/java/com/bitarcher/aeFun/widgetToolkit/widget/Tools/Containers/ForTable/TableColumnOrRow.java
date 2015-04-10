package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.ForTable;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ISpaceUsage;

/**
 * Created by michel on 04/03/15.
 */
public class TableColumnOrRow implements  ITableColumnOrRow {

    ISpaceUsage spaceUsage;
    float scalar;

    @Override
    public ISpaceUsage getSpaceUsage() {
        return this.spaceUsage;
    }

    public TableColumnOrRow(ISpaceUsage spaceUsage) {
        this.spaceUsage = spaceUsage;
    }

    @Override
    public float getScalar() {
        return scalar;
    }

    @Override
    public void setScalar(float scalar) {
        this.scalar = scalar;
    }

    @Override
    public float getScalarWithoutTwoMargin()
    {
        float retval = this.scalar - (this.getSpaceUsage().getMargin() * 2);

        if(retval < 0)
        {
            retval = 0;
        }

        return retval;
    }
}
