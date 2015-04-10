package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ISpaceUsage;

/**
 * Created by michel on 01/03/15.
 */
class WidgetAndSpaceUsageTupleForBox extends WidgetAndSpaceUsageTuple  implements ISpaceUsageOwnerAndScalar {
    float scalar;

    public WidgetAndSpaceUsageTupleForBox(IWidget widget, ISpaceUsage spaceUsage) {
        super(widget, spaceUsage);
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
