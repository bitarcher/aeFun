package com.bitarcher.aeFun.interfaces.colors;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 23/04/15.
 */
public interface IHSB {

    float getHue();
    void setHue(float hue);
    float getSaturation();
    void setSaturation(float saturation);
    float getBrightness();
    void setBrightness(float brightness);
}
