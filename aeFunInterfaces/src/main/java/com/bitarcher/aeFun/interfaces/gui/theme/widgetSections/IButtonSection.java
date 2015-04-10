package com.bitarcher.aeFun.interfaces.gui.theme.widgetSections;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 10/03/15.
 */
public interface IButtonSection extends ISection {
    float getBorderSize();
    Color getBorderColor();
    Color getNormalColor1();
    Color getNormalColor2();
    Color getActivatedColor1();
    Color getActivatedColor2();
    Color getDisabledColor1();
    Color getDisabledColor2();
}
