package com.bitarcher.aeFun.interfaces.colors;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 23/04/15.
 */
public interface IColorSpaces {
    Color HSB_to_Color(IHSB hsb);
    IHSB color_to_HSB(Color color);
}
