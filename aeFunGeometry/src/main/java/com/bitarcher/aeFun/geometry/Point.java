package com.bitarcher.aeFun.geometry;

import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.IVector;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 25/03/15.
 */
public class Point implements IPoint {
    float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public IPoint translate(IVector vector) {
        return new Point(this.getX() + vector.getX(), this.getY() + vector.getY());
    }

    @Override
    public IVector substract(IPoint point) {
        return new Vector(this.getX() - point.getX(), this.getY() - point.getY());
    }
}
