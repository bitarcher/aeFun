package com.bitarcher.aeFun.geometry.primitives.helpers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.PositionAndSizeOwner;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;

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

    public float[] getBufferData(int numOfTriangles)
    {
        float UNUSED = 0;
        int numOfVertices = this.getNumOfVertices(numOfTriangles);
        int numOfLines = numOfVertices / 2;
        int numOfRows = numOfLines - 2;
        float[] retval = new float[numOfVertices * 3];


        float w2 = this.positionAndSizeOwner.getSize().getWidth() / 2;
        float h2 = this.positionAndSizeOwner.getSize().getHeight() / 2;
        float startX = this.positionAndSizeOwner.getPosition().getX() - w2;
        float endX = this.positionAndSizeOwner.getPosition().getX() + w2;
        float startY = this.positionAndSizeOwner.getPosition().getX() - h2;
        float endY = this.positionAndSizeOwner.getPosition().getX() + h2;

        float yIncrement = this.getPositionAndSizeOwner().getSize().getHeight() / numOfRows;

        // first two points have special order

        // p0
        retval[0] = startX;
        retval[1] = startY;
        retval[2] = UNUSED;

        // p1
        retval[3] = endX;
        retval[4] = startY;
        retval[5] = UNUSED;


        float currentY = startY + yIncrement;

        for(int i = 6; i < retval.length ; i+=6)
        {
            // right
            retval[i] = endX;
            retval[i+1] = currentY;
            retval[i+2] = UNUSED;

            // left
            retval[i+3] = startX;
            retval[i+4] = currentY;
            retval[i+5] = UNUSED;

            currentY += yIncrement;
        }

        return retval;
    }

    public int getNumOfVertices(int numOfTriangles)
    {
        return numOfTriangles + 2;
    }
}
