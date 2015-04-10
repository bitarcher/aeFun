package com.bitarcher.aeFun.interfaces.gui.widgets.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.context.INoneContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;

import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.EnumOrientation;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.IOriginalWidthAndHeightResetable;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.IShouldFixedSpaceUsageBeResizedOnResize;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ISpaceUsage;

/**
 * Created by michel on 28/02/15.
 */
public interface IBox extends IContainer<INoneContext>, IOriginalWidthAndHeightResetable, IShouldFixedSpaceUsageBeResizedOnResize {

    EnumOrientation getOrientation();


    void packStart(IWidget widget, ISpaceUsage spaceUsage);
    void packEnd(IWidget widget, ISpaceUsage spaceUsage);
    void detachChild(IWidget widget);

}

