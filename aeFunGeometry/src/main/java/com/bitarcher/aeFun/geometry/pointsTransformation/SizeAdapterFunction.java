package com.bitarcher.aeFun.geometry.pointsTransformation;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.geometry.pointsTransformation.IPointToPointFunction;

/**
 * Created by michel on 17/04/15.
 */
public class SizeAdapterFunction implements IPointToPointFunction {
    ISize designSize;
    ISize outputSize;
    float widthFactor;
    float heightFactor;

    public SizeAdapterFunction(ISize designSize, ISize outputSize) {
        this.designSize = designSize;
        this.outputSize = outputSize;

        this.widthFactor = outputSize.getWidth() / designSize.getWidth();
        this.heightFactor = outputSize.getHeight() / designSize.getHeight();
    }

    public SizeAdapterFunction(float designWidth, float designHeight, float outputWidth, float outputHeight) {
        this(new Size(designWidth, designHeight), new Size(outputWidth, outputHeight));
    }

    @Override
    public IPoint getYByX(IPoint xValue) {
        return new Point(xValue.getX() * this.widthFactor, xValue.getY() * this.heightFactor);
    }
}
