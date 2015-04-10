package com.bitarcher.aeFun.interfaces.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 04/03/15.
 */
public interface IPositionable extends IPosition{
    void setPosition(float x, float y);

    void setX(float x);
    void setY(float y);
}
