package com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import android.content.Context;

import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IAnalogOnScreenControlSection;
import com.bitarcher.aeFun.interfaces.mvc.IImage;

/**
 * Created by michel on 12/04/15.
 */
public class DefaultAnalogOnScreenControlSection implements IAnalogOnScreenControlSection {

    Context context;

    public DefaultAnalogOnScreenControlSection(Context context) {
        this.context = context;
    }

    @Override
    public IImage getBaseImage() {
        return ResourceInfosSingleton.getInstance(this.context).getAnalogOnScreenControlSectionBaseImage();
    }

    @Override
    public IImage getKnobImage() {
        return ResourceInfosSingleton.getInstance(this.context).getAnalogOnScreenControlSectionKnobImage();
    }
}

