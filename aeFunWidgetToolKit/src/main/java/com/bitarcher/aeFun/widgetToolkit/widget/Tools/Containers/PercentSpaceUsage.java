package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.IPercentSpaceUsage;

/**
 * Created by michel on 02/03/15.
 */
public class PercentSpaceUsage extends SpaceUsage implements IPercentSpaceUsage {
    float percentSpaceUsage;

    @Override
    public float getPercentSpaceUsage() {
        return this.percentSpaceUsage;
    }

    public PercentSpaceUsage(float margin, float percentSpaceUsage) {
        super(margin);
        this.percentSpaceUsage = percentSpaceUsage;
    }
}
