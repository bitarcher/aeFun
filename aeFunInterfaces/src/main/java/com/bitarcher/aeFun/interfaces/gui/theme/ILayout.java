package com.bitarcher.aeFun.interfaces.gui.theme;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.context.IContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceRequirementsStackUser;

/**
 * Created by michel on 09/03/15.
 */
public interface ILayout<TContext extends IContext> extends IResourceRequirementsStackUser {

    IWidget<TContext> getWidget();

    // create the sub entity and attach the to the widget, called by Widget ctor
    void onPopulate();

    // should be called by widget sub class ctor
    void onInit();

    TContext getContext();
}
