package com.bitarcher.aeFun.geometry.primitives;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.list.SmartList;

/**
 * Created by michel on 17/04/15.
 */
public class BezierFilledEllipsoid extends Mesh {

    public BezierFilledEllipsoid(float pX, float pY, IPoint ellipsoidTriangleFanCenter, SmartList<IPoint> points, int numOfAdditionnalPointsBetweenPoints, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, BezierFilledEllipsoid.computeBufferData(ellipsoidTriangleFanCenter, points, numOfAdditionnalPointsBetweenPoints),
                BezierFilledEllipsoid.getVertexCount(ellipsoidTriangleFanCenter, points, numOfAdditionnalPointsBetweenPoints), DrawMode.TRIANGLE_FAN, pVertexBufferObjectManager);
    }

    static float[] computeBufferData(IPoint ellipsoidTriangleFanCenter, SmartList<IPoint> points, int numOfAdditionnalPointsBetweenPoints)
    {
        float[] retval = null;

        BezierFilledEllipsoidHelper helper = new BezierFilledEllipsoidHelper(ellipsoidTriangleFanCenter, points, numOfAdditionnalPointsBetweenPoints);

        retval = helper.computeBufferData();

        return retval;
    }

    static int getVertexCount(IPoint ellipsoidTriangleFanCenter, SmartList<IPoint> points, int numOfAdditionnalPointsBetweenPoints)
    {
        BezierFilledEllipsoidHelper helper = new BezierFilledEllipsoidHelper(ellipsoidTriangleFanCenter, points, numOfAdditionnalPointsBetweenPoints);

        return helper.getVertexCount();
    }
}
