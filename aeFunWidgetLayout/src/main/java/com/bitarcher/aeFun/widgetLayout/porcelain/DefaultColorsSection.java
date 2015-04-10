package com.bitarcher.aeFun.widgetLayout.porcelain;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.IColorsSection;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;

import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 11/03/15.
 */
public class DefaultColorsSection implements IColorsSection {
    ITheme theme;

    public ITheme getTheme() {
        return theme;
    }

    public DefaultColorsSection(ITheme theme) {
        this.theme = theme;
    }

    @Override
    public Color getFontColor() {
        return new Color(0,0,0);
    }

    @Override
    public Color getBorderColor() {
        return new Color(0.7f, 0.8f, 1f);
    }

    @Override
    public Color getNormalColor1() {
        return new Color(124f / 256f, 159f / 256f, 245f / 256f);
    }

    @Override
    public Color getNormalColor2() {
        return new Color(174f / 256f, 195f / 256f, 250f / 256f);
    }

    @Override
    public Color getActivatedColor1() {
        return new Color(0.6f, 0.8f, 1f);
    }

    @Override
    public Color getActivatedColor2() {
        // dark red
        return new Color(0.8f, 0, 0);
    }

    @Override
    public Color getDisabledColor1() {
        return new Color(0.7f, 0.7f, 0.7f);
    }

    @Override
    public Color getDisabledColor2() {
        return new Color(0.8f, 0.8f, 0.8f);
    }
}
