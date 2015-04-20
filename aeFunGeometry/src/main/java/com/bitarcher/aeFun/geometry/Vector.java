package com.bitarcher.aeFun.geometry;


import com.bitarcher.aeFun.interfaces.geometry.IPosition;
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

    public Vector(IPosition position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    @Override
    public IVector getNeg() {
        return new Vector(-this.x, -this.y);
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
    public float getNorm() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public IVector getNewVectorWithSameDirectionAndWuthTheFollowingNorm(float norm) {
        float myNorm = this.getNorm();
        float normFactor = myNorm / norm;

        Vector retval = new Vector(this.x * normFactor, this.y * normFactor);

        return retval;
    }
}
