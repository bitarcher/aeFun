package com.bitarcher.aeFun.interfaces.gui.theme.layout;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ILayout;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IAnalogOnScreenControlContext;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;

/**
 * Created by michel on 12/04/15.
 */
public interface IAnalogOnScreenControlLayout extends ILayout<IAnalogOnScreenControlContext> {
    void setAnalogOnScreenControlListener(AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener);
}
