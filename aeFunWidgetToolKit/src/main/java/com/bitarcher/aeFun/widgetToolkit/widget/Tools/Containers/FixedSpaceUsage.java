package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.IFixedSpaceUsage;

/**
 * Created by michel on 02/03/15.
 */
public class FixedSpaceUsage extends SpaceUsage implements IFixedSpaceUsage {
    float fixedSpaceUsage;

    @Override
    public float getFixedSpaceUsage() {
        return this.fixedSpaceUsage;
    }

    public FixedSpaceUsage(float margin, float fixedSpaceUsage) {
        super(margin);
        this.fixedSpaceUsage = fixedSpaceUsage;
    }
}
