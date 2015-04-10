package com.bitarcher.aeFun.interfaces.gui.theme;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.context.IContext;

/**
 * Created by michel on 09/03/15.
 */
public interface ILayoutOwner<TContext extends IContext> {
    ILayout<TContext> getLayout();
}
