package com.bitarcher.aeFun.geometry.pointsTransformation;

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Vector;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.IVector;
import com.bitarcher.aeFun.interfaces.geometry.pointsTransformation.IPointToPointFunction;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Useful to jump from Gimp coordinates to OpenGl coordinates
 */
public class FlipYAxisFunction implements IPointToPointFunction {
    float height;

    public FlipYAxisFunction(float height) {
        this.height = height;
    }

    @Override
    public IPoint getYByX(IPoint xValue) {

        IPoint retval = new Point(xValue.getX(), this.height - xValue.getY());

        return retval;
    }
}
