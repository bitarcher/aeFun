package com.bitarcher.aeFun.interfaces.gui.widgets;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.context.ICheckableContext;

/**
 * Created by michel on 20/03/15.
 */
public interface ICheckable<TContext extends ICheckableContext> extends IWidget<TContext>, com.bitarcher.aeFun.interfaces.basicioc.ICheckable {
    void addCheckableListener(ICheckableListener checkableListener);
    void removeCheckableListener(ICheckableListener checkableListener);


}
