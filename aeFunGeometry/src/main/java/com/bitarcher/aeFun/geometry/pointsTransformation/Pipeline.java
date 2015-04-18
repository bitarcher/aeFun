package com.bitarcher.aeFun.geometry.pointsTransformation;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.TPipeline;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.pointsTransformation.IPipeline;

import org.andengine.util.adt.list.SmartList;

import java.util.List;

/**
 * Created by michel on 17/04/15.
 */
public class Pipeline extends TPipeline<IPoint> implements IPipeline {

    @Override
    public float[] applyAndGet3VfPoints(List<IPoint> inputPoints)
    {
        float UNUSED = 0;
        float[] retval = new float[inputPoints.size() * 3];

        int index = 0;

        for(IPoint point : inputPoints)
        {
            IPoint transformedPoint = this.getYByX(point);

            retval[index] = transformedPoint.getX();
            retval[index + 1] = transformedPoint.getY();
            retval[index + 2] = UNUSED;
            index+=3;
        }

        return retval;
    }

    @Override
    public SmartList<IPoint> applyOnList(List<IPoint> inputPoints) {
        SmartList<IPoint> retval = new SmartList<>();

        for(IPoint point : inputPoints) {
            IPoint transformedPoint = this.getYByX(point);

            retval.addLast(transformedPoint);
        }

        return retval;
    }
}
