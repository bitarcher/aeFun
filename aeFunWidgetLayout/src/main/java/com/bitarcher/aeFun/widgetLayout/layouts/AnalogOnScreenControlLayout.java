package com.bitarcher.aeFun.widgetLayout.layouts;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IAnalogOnScreenControlContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.IAnalogOnScreenControlLayout;
import com.bitarcher.aeFun.interfaces.gui.widgets.IAnalogOnScreenControl;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;

/**
 * Created by michel on 12/04/15.
 */
public class AnalogOnScreenControlLayout implements IAnalogOnScreenControlLayout, IAnalogOnScreenControlContext {
    IAnalogOnScreenControl analogOnScreenControlWidget;
    AnalogOnScreenControl aeAnalogOnScreenControl;


    public AnalogOnScreenControlLayout(IAnalogOnScreenControl analogOnScreenControlWidget) {
        this.analogOnScreenControlWidget = analogOnScreenControlWidget;


    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public IAnalogOnScreenControl getWidget() {
        return this.analogOnScreenControlWidget;
    }

    @Override
    public void onPopulate() {
        //this.aeAnalogOnScreenControl = new AnalogOnScreenControl()
    }

    @Override
    public void onInit() {

    }

    @Override
    public IAnalogOnScreenControlContext getContext() {
        return this;
    }

    @Override
    public void setPadding(float padding) {

    }

    @Override
    public void pushResourceRequirements() {

    }

    @Override
    public void popResourceRequirements() {

    }

    @Override
    public void setSize(ISize size) {

    }
}
