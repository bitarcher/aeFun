package com.bitarcher.aeFun.colors;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.colors.IGradientMaker;

import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 23/04/15.
 */
public class GradientMaker implements IGradientMaker {
    Color startColor;
    Color endColor;

    public Color getStartColor() {
        return startColor;
    }

    public Color getEndColor() {
        return endColor;
    }

    public GradientMaker(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    @Override
    public Color getColor(float distanceFromStart) {
        float r = distanceFromStart * endColor.getRed() + (1 - distanceFromStart) * startColor.getRed();
        float g = distanceFromStart * endColor.getGreen() + (1 - distanceFromStart) * startColor.getGreen();
        float b = distanceFromStart * endColor.getBlue() + (1 - distanceFromStart) * startColor.getBlue();

        Color retval = new Color(r, g, b);

        return retval;
    }
}


