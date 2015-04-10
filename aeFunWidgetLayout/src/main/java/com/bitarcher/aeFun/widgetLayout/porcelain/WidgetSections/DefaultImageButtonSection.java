package com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IImageButtonSection;

import org.andengine.util.adt.color.Color;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 10/03/15.
 */
public class DefaultImageButtonSection implements IImageButtonSection {
    ITheme theme;

    public DefaultImageButtonSection(ITheme theme) {
        this.theme = theme;
    }



    @Override
    public float getBorderSize() {
        return 2;
    }

    @Override
    public Color getBorderColor() {
        return this.theme.getColorsSection().getBorderColor();
    }

    @Override
    public Color getNormalColor1() {
        return this.theme.getColorsSection().getNormalColor1();
    }

    @Override
    public Color getNormalColor2() {
        return this.theme.getColorsSection().getNormalColor2();
    }

    @Override
    public Color getActivatedColor1() {
        return this.theme.getColorsSection().getActivatedColor1();
    }

    @Override
    public Color getActivatedColor2() {
        return this.theme.getColorsSection().getActivatedColor2();
    }

    @Override
    public Color getDisabledColor1() {
        return this.theme.getColorsSection().getDisabledColor1();
    }

    @Override
    public Color getDisabledColor2() {
        return this.theme.getColorsSection().getDisabledColor2();
    }
}
