package com.bitarcher.aeFun.widgetLayout.layouts.tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.gui.theme.context.ICheckableContext;

/**
 * Created by michel on 20/03/15.
 */
public abstract class CheckableContext  implements ICheckableContext{

    @Override
    public void setPadding(float padding) {
        this.doSizeAndPadding();
    }

    @Override
    public void setSize(ISize size) {
        this.doSizeAndPadding();
    }

    protected abstract void doSizeAndPadding();
}
