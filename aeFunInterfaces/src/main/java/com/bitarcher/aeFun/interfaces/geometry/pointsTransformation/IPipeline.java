package com.bitarcher.aeFun.interfaces.geometry.pointsTransformation;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ITPipeline;

import org.andengine.util.adt.list.SmartList;

import java.util.List;

/**
 * Created by michel on 17/04/15.
 */
public interface IPipeline extends ITPipeline<IPoint>, IPointToPointFunction {
    float[] applyAndGet3VfPoints(List<IPoint> inputPoints);
    SmartList<IPoint> applyOnList(List<IPoint> inputPoints);
}
