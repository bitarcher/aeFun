package com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections;

import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.ICheckButtonSection;


import org.andengine.opengl.font.Font;
import org.andengine.util.adt.color.Color;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 20/03/15.
 */
public class DefaultCheckButtonSection implements ICheckButtonSection {
    ITheme theme;

    public DefaultCheckButtonSection(ITheme theme) {
        this.theme = theme;
    }

    @Override
    public Font getFont() {
        return this.theme.getFontThemeSection().getFont(EnumFontSize.Medium);
    }

    @Override
    public float getSideLength() {
        return 30;
    }

    @Override
    public Color getActivatedColor2() {
        return this.theme.getColorsSection().getActivatedColor2();
    }

    @Override
    public Color getClickableEntityColor1() {
        return this.theme.getColorsSection().getNormalColor1();
    }

    @Override
    public Color getClickableEntityColor2() {
        return this.theme.getColorsSection().getNormalColor2();
    }


    @Override
    public Color getDisabledColor1() {
        return this.theme.getColorsSection().getDisabledColor1();
    }

    @Override
    public Color getDisabledColor2() {
        return this.theme.getColorsSection().getDisabledColor2();
    }

    @Override
    public Color getDisabledColor3() {
        Color retval = Color.WHITE;
        return retval;
    }
}

