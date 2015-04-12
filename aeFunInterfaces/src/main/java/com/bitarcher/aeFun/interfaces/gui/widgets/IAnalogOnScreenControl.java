package com.bitarcher.aeFun.interfaces.gui.widgets;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.context.IAnalogOnScreenControlContext;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;

/**
 * Created by michel on 12/04/15.
 */
public interface IAnalogOnScreenControl extends IWidget<IAnalogOnScreenControlContext> {
    void addAnalogOnScreenControlListener(AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener);
    void removeAnalogOnScreenControlListener(AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener);
}
