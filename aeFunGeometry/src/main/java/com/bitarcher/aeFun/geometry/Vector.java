package com.bitarcher.aeFun.geometry;


import com.bitarcher.aeFun.interfaces.geometry.IVector;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 25/03/15.
 */
public class Vector implements IVector {
    float x, y;

    public Vector(float x, float y) {
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
}
