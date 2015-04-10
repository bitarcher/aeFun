package com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;

import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IImageSection;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 10/03/15.
 */
public class DefaultImageSection implements IImageSection {
    ITheme theme;

    public DefaultImageSection(ITheme theme) {
        this.theme = theme;
    }

}
