package com.bitarcher.aeFun.geometry.pointsTransformation;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.geometry.pointsTransformation.IPointToPointFunction;

/**
 * Created by michel on 18/04/15.
 */
public class WindFunction implements IPointToPointFunction {
    float windStrength = 0;
    EnumSide windSide = EnumSide.Right;
    ISize size;


    public float getWindStrength() {
        return windStrength;
    }

    public EnumSide getWindSide() {
        return windSide;
    }

    public ISize getSize() {
        return size;
    }

    public WindFunction(float windStrength, EnumSide windSide, ISize size) {
        this.windStrength = windStrength;
        this.windSide = windSide;
        this.size = size;
    }

    @Override
    public IPoint getYByX(IPoint xValue) {
        float yRatio = xValue.getY() / this.size.getHeight();
        float yRR = yRatio * yRatio;
        float ss = this.windStrength * this.windStrength;

        double v = Math.sqrt(yRatio + this.windStrength);
        double radius = xValue.getY();



        double v2 = Math.PI / 2 - v;

        double v3 = Math.sqrt(v2);
        double xDiff = Math.cos(v3) * radius;
        double y3 = Math.sin(v2) * radius;
        double invWindStrength = 1 - this.windStrength;

        double newX = (xValue.getX() / 2 + (float)xDiff / 4) * 1.5;
        if(windSide == EnumSide.Left)
        {
            newX = (xValue.getX() / 2 - (float)xDiff / 4) * 1.5;
        }

        double newY = (xValue.getY() / 2 + (float)y3 / 3) * 1.5;

        double newX2 = newX * this.windStrength + xValue.getX() * invWindStrength;
        double newY2 = newY * this.windStrength + xValue.getY() * invWindStrength;

        Point retval = new Point((float)newX2 , (float)newY2);

        return retval;
    }
}

