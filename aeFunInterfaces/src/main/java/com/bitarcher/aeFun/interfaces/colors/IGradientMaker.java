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
public interface IGradientMaker {

    Color getStartColor();
    Color getEndColor();

    /**
     *
     * @param distanceFromStart between 0.0f and 1.0f
     * @return a weighted Color
     */
    Color getColor(float distanceFromStart);
}
