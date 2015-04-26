package com.bitarcher.aeFun.colors;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.colors.IHSB;

/**
 * Created by michel on 23/04/15.
 */
public class HSB implements IHSB {
    float hue;
    float saturation;
    float brightness;

    public float getHue() {
        return hue;
    }

    public void setHue(float hue) {

        if(hue > 1)
            hue = 1;

        if(hue < 0)
            hue = 0;

        this.hue = hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation)
    {
        if(saturation > 1)
            saturation = 1;

        if(saturation < 0)
            saturation = 0;
        this.saturation = saturation;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {

        if(brightness > 1)
            brightness = 1;

        if(brightness < 0)
            brightness = 0;

        this.brightness = brightness;
    }

    public HSB(float hue, float saturation, float brightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }

    public HSB() {
    }

    public HSB(IHSB hsb)
    {
        this.hue = hsb.getHue();
        this.saturation = hsb.getSaturation();
        this.brightness = hsb.getBrightness();
    }
}
