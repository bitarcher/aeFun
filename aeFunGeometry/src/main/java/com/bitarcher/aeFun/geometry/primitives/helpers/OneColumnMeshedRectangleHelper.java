package com.bitarcher.aeFun.geometry.primitives.helpers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.PositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michel on 21/04/15.
 */
public class OneColumnMeshedRectangleHelper {
    IPositionAndSizeOwner positionAndSizeOwner;

    public IPositionAndSizeOwner getPositionAndSizeOwner() {
        return positionAndSizeOwner;
    }

    public OneColumnMeshedRectangleHelper(IPositionAndSizeOwner positionAndSizeOwner) {
        this.positionAndSizeOwner = positionAndSizeOwner;
    }

    public OneColumnMeshedRectangleHelper(float x, float y, float width, float height) {
        this(new PositionAndSizeOwner(x, y, width, height));
    }

    public float[] getBufferDataForTriangleStripMode(int numOfTriangles)
    {
        List<IPoint> points = this.getTriangleStripModePoints(numOfTriangles);
        float[] retval = new float[points.size() * 3];

        float UNUSED = 0;
        int i = 0;
        for(IPoint point : points)
        {
            retval[i] = point.getX();
            i++;
            retval[i] = point.getY();
            i++;
            retval[i] = UNUSED;
            i++;
        }

        return retval;
    }

    public float[] getVerticesBufferData2VForTrianglesMode(int numOfTriangles)
    {
        List<IPoint> points = this.getTrianglesModePoints(numOfTriangles);
        float[] retval = new float[points.size() * 2];

        float UNUSED = 0;
        int i = 0;
        for(IPoint point : points)
        {
            retval[i] = point.getX();
            i++;
            retval[i] = point.getY();
            i++;
        }

        return retval;
    }

    public List<IPoint> getTriangleStripModePoints(int numOfTriangles)
    {
        int numOfVertices = this.getNumOfVertices(numOfTriangles);
        int numOfLines = numOfVertices / 2;
        int numOfRows = numOfLines - 1;
        ArrayList<IPoint> retval = new ArrayList<>();


        float w2 = this.positionAndSizeOwner.getSize().getWidth() / 2;
        float h2 = this.positionAndSizeOwner.getSize().getHeight() / 2;
        float startX = this.positionAndSizeOwner.getPosition().getX() - w2;
        float endX = this.positionAndSizeOwner.getPosition().getX() + w2;
        float startY = this.positionAndSizeOwner.getPosition().getY() - h2;
        //float endY = this.positionAndSizeOwner.getPosition().getY() + h2;  // not used

        float yIncrement = this.getPositionAndSizeOwner().getSize().getHeight() / numOfRows;

        // first two points have special order

        retval.add(new Point(startX, startY));
        retval.add(new Point(endX, startY));


        float currentY = startY + yIncrement;

        for(int i = 2; i < numOfVertices ; i+=2)
        {
            // right
            retval.add(new Point(endX, currentY));

            // left
            retval.add(new Point(startX, currentY));

            currentY += yIncrement;
        }

        return retval;
    }


    public List<IPoint> getTrianglesModePoints(int numOfTriangles)
    {
        int numOfVertices = this.getNumOfVertices(numOfTriangles);
        int numOfLines = numOfVertices / 2;
        int numOfRows = numOfLines - 1;
        ArrayList<IPoint> retval = new ArrayList<>();


        float w2 = this.positionAndSizeOwner.getSize().getWidth() / 2;
        float h2 = this.positionAndSizeOwner.getSize().getHeight() / 2;
        float startX = this.positionAndSizeOwner.getPosition().getX() - w2;
        float endX = this.positionAndSizeOwner.getPosition().getX() + w2;
        float startY = this.positionAndSizeOwner.getPosition().getY() - h2;
        //float endY = this.positionAndSizeOwner.getPosition().getY() + h2;  // not used

        float yIncrement = this.getPositionAndSizeOwner().getSize().getHeight() / numOfRows;


        float currentY = startY;

        for(int i = 0; i < numOfRows ; i++)
        {
            float nextY = currentY + yIncrement;

            Point p0 = new Point(startX, currentY);
            Point p1 = new Point(endX, currentY);
            Point p2 = new Point(endX, nextY);
            Point p3 = new Point(startX, nextY);

            // triangle bottom right
            retval.add(p0);
            retval.add(p1);
            retval.add(p2);

            // triangle top left
            retval.add(p0);
            retval.add(p2);
            retval.add(p3);

            currentY = nextY;
        }

        return retval;
    }


    public int getNumOfVertices(int numOfTriangles)
    {
        return numOfTriangles + 2;
    }
}
