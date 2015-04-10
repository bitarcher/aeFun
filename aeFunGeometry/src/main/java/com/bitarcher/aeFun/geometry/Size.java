package com.bitarcher.aeFun.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.ISize;

/**
 * Created by michel on 25/03/15.
 */
public class Size implements ISize {
    float width, height;

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public Size(float width, float height) {
        this.width = width;
        this.height = height;
    }
}
