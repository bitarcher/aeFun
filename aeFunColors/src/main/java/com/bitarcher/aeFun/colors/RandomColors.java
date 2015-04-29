package com.bitarcher.aeFun.colors;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.colors.IRandomColors;

import org.andengine.util.adt.color.Color;

import java.util.Random;

/**
 * Created by michel on 29/04/15.
 */
public class RandomColors implements IRandomColors{
    Random random;

    public RandomColors(Random random) {
        this.random = random;
    }

    public RandomColors() {
        this.random = new Random();
    }

    @Override
    public Color getRandomColor() {
        float r, g, b;

        r = this.random.nextFloat();
        g = this.random.nextFloat();
        b = this.random.nextFloat();

        return new Color(r, g, b);
    }

    @Override
    public Color getRandomColor(Color c1, Color c2) {
        GradientMaker gradientMaker = new GradientMaker(c1, c2);

        float f = this.random.nextFloat();

        Color retval = gradientMaker.getColor(f);

        return retval;
    }

    @Override
    public Color getRandomColor(Color c1, Color c2, Color c3) {

        Color c2c3 = this.getRandomColor(c2, c3);
        Color retval1 = this.getRandomColor(c1, c2c3);
        Color c1c2 = this.getRandomColor(c1, c2);
        Color retval2 = this.getRandomColor(c1c2, c3);
        Color retval = this.getRandomColor(retval1, retval2);

        return retval;
    }

    @Override
    public Color getRandomColor(Color c1, Color c2, Color c3, Color c4) {
        Color c2c3c4 = this.getRandomColor(c2, c3, c4);
        Color retval1 = this.getRandomColor(c1, c2c3c4);

        Color c1c3c4 = this.getRandomColor(c1, c3, c4);
        Color retval2 = this.getRandomColor(c2, c1c3c4);

        Color c1c2c3 = this.getRandomColor(c1, c2, c3);
        Color retval3 = this.getRandomColor(c4, c1c2c3);

        Color retval = this.getRandomColor(retval1, retval2, retval3);

        return retval;
    }
}

