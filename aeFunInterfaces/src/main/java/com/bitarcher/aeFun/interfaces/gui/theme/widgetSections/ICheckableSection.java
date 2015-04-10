package com.bitarcher.aeFun.interfaces.gui.theme.widgetSections;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import org.andengine.opengl.font.Font;
import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 20/03/15.
 */
public interface ICheckableSection extends ISection {
    Color getClickableEntityColor1();
    Color getClickableEntityColor2();
    Color getActivatedColor2();
    Color getDisabledColor1(); // the border
    Color getDisabledColor2(); // the check symbol
    Color getDisabledColor3(); // the empty space
    float getSideLength();
    Font getFont();
}
