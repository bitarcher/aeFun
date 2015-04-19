package com.bitarcher.aeFun.interfaces.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 17/04/15.
 */
public interface IPoint extends IPosition {
    IPoint translate(IVector vector);
    IVector substract(IPoint point);
}
