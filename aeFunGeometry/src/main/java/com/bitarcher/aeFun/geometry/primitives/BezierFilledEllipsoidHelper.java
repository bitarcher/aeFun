package com.bitarcher.aeFun.geometry.primitives;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.util.adt.list.SmartList;

/**
 * Created by michel on 17/04/15.
 */
public class BezierFilledEllipsoidHelper {
    IPoint ellipsoidTriangleFanCenter;
    SmartList<IPoint> points;
    int numOfAdditionnalPointsBetweenPoints;

    public BezierFilledEllipsoidHelper(IPoint ellipsoidTriangleFanCenter, SmartList<IPoint> points, int numOfAdditionnalPointsBetweenPoints) {
        this.ellipsoidTriangleFanCenter = ellipsoidTriangleFanCenter;
        this.points = points;
        this.numOfAdditionnalPointsBetweenPoints = numOfAdditionnalPointsBetweenPoints;
    }

    public float[] computeBufferData()
    {
        float UNUSED = 0;

        float[] retval = new float[this.getVertexCount() * 3];

        retval[0] = ellipsoidTriangleFanCenter.getX();
        retval[1] = ellipsoidTriangleFanCenter.getY();
        retval[2] = UNUSED;

        int i = 3;

        SmartList<IPoint> allPoints = this.getBezierPoints();

        for(IPoint point : allPoints)
        {
            retval[i] = point.getX();
            retval[i + 1] = point.getY();
            retval[i + 2] = UNUSED;
            i+= 2;
        }

        return retval;
    }

    public int getVertexCount()
    {
        int retval = 0;

        retval += 1; // center

        int numOfStrongPoints = this.points.size();
        retval += numOfStrongPoints;
        retval += (numOfStrongPoints -1) * this.numOfAdditionnalPointsBetweenPoints;

        return retval;
    }

    public SmartList<IPoint> getBezierPoints()
    {
        SmartList<IPoint> retval = new SmartList<>();

        int controlPointsSize = this.points.size();

        for(int i=0 ; i < (controlPointsSize ) ; i+=3)
        {
            IPoint p0 = this.points.get((i + 0 ) % controlPointsSize);
            IPoint p1 = this.points.get((i + 1 ) % controlPointsSize);
            IPoint p2 = this.points.get((i + 2 ) % controlPointsSize);
            IPoint p3 = this.points.get((i + 3 ) % controlPointsSize);

            this.addBezierPoints(retval, p0, p1, p2, p3);
        }

        return  retval;
    }

    void addBezierPoints(SmartList<IPoint> pointsList, IPoint p0, IPoint p1, IPoint p2, IPoint p3)
    {
        float t = 0;
        int numOfSegments = this.numOfAdditionnalPointsBetweenPoints + 1;

        for(int i = 0; i <= numOfSegments; i++)
        {
            t = i / (float) numOfSegments;
            IPoint newPoint = this.calculateBezierPoint(t, p0, p1, p2, p3);
            pointsList.addLast(newPoint);
        }
    }

    /**
     Calculates a point on the Bezier curve represented with the four controlpoints given.
     */
    private IPoint calculateBezierPoint(float t, IPoint p0, IPoint p1, IPoint p2, IPoint p3)
    {
        float u = 1 - t;
        float tt = t * t;
        float uu = u * u;
        float uuu = uu * u;
        float ttt = tt * t;

        float x, y;

        x = uuu * p0.getX(); //first term
        y = uuu * p0.getY();

        x += 3 * uu * t * p1.getX(); //second term
        y += 3 * uu * t * p1.getY(); //second term


        x += 3 * u * tt * p2.getX(); //third term
        y += 3 * u * tt * p2.getY(); //third term

        x += ttt * p3.getX(); //fourth term
        y += ttt * p3.getY(); //fourth term

        Point retval = new Point(x, y);

        return retval;
    }
}
