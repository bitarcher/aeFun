package com.bitarcher.aeFun.geometry.pointsTransformation;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
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
    IVector centerPointPlusVector;
    IVector centerPointMinusVector;

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
        this.centerPointPlusVector = new Vector(centerPoint);
        this.centerPointMinusVector = this.centerPointPlusVector.getNeg();
    }

    @Override
    public IPoint getYByX(IPoint xValue) {
        // @see http://fr.wikipedia.org/wiki/Rotation_plane


        IPoint tPoint = xValue.translate(this.centerPointMinusVector);

        double x = tPoint.getX();
        double y = tPoint.getY();

        double xPrime = x * this.cosAngle + y * this.sinAngle;
        double yPrime = -x * this.sinAngle + y * this.cosAngle;


        Point point = new Point((float)xPrime, (float)yPrime);


        IPoint retval = point.translate(this.centerPointPlusVector);

        //IPoint retval = point;

        return retval;
    }
}
