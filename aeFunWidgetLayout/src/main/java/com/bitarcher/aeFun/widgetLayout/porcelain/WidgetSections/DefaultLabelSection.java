package com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections;

import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.ILabelSection;


import org.andengine.opengl.font.Font;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 10/03/15.
 */
public class DefaultLabelSection implements ILabelSection {
    ITheme theme;

    public DefaultLabelSection(ITheme theme) {
        this.theme = theme;
    }

    @Override
    public Font getLabelFont() {
        return this.theme.getFontThemeSection().getFont(EnumFontSize.Medium);
    }
}
