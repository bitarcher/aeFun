package com.bitarcher.aeFun.geometry;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.IPosition;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

/**
 * Created by michel on 25/03/15.
 */
public class PositionAndSizeOwner implements IPositionAndSizeOwner {
    IPosition position;
    ISize size;

    public PositionAndSizeOwner(IPosition position, ISize size) {
        this.position = position;
        this.size = size;
    }

    public PositionAndSizeOwner(float x, float y, float width, float height) {
        this(new Position(x, y), new Size(width, height));
    }

    @Override
    public IPosition getPosition() {
        return position;
    }

    @Override
    public ISize getSize() {
        return size;
    }
}
