package com.bitarcher.aeFun.geometry.pointsTransformation;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Vector;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.IVector;
import com.bitarcher.aeFun.interfaces.geometry.pointsTransformation.IPointToPointFunction;

/**
 * Created by michel on 19/04/15.
 */
public class RotationFunction implements IPointToPointFunction {
    IPoint centerPoint;
    float radianAngle;
    double cosAngle;
    double sinAngle;

    public IPoint getCenterPoint() {
        return centerPoint;
    }

    public float getRadianAngle() {
        return radianAngle;
    }

    public RotationFunction(IPoint centerPoint, float radianAngle) {
        this.centerPoint = centerPoint;
        this.radianAngle = radianAngle;
        this.cosAngle = Math.cos(radianAngle);
        this.sinAngle = Math.sin(radianAngle);
    }

    @Override
    public IPoint getYByX(IPoint xValue) {
        // @see http://homeomath.imingo.net/rotation.htm

        IVector v = xValue.substract(centerPoint);

        double x = v.getX();
        double y = v.getY();

        double xPrime = x * this.cosAngle - y * this.sinAngle;
        double yPrime = y * this.cosAngle + y * this.sinAngle;

        // TODO

        return null;
    }
}
