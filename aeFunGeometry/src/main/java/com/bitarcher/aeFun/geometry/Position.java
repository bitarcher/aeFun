package com.bitarcher.aeFun.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.IPosition;

/**
 * Created by michel on 25/03/15.
 */
public class Position implements IPosition {
    float x, y;

    public Position(float x, float y) {
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
