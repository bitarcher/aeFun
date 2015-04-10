package com.bitarcher.aeFun.geometry.primitives;

import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 08/04/15.
 */
public class CheckSymbol extends Mesh {

    public CheckSymbol(float x, float y, float width, float height, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(x, y, width, height, CheckSymbol.getCoordinates(width, height), 6, DrawMode.TRIANGLE_STRIP, pVertexBufferObjectManager);


        // blue by default, but the color has to be set by the container
        this.setColor(0, 0, 1);
    }

    static float[] getCoordinates(float width, float height)
    {
        float UNUSED = 0;

        // coordinates for triangle_strip
        float[] retval = new float[]{
            // p0
            0.05f * width, 0.6f * height, UNUSED,
            // p1
            0, 0.5f * height, UNUSED,
            // p2
            0.4f * width, 0, UNUSED,
            // p3
            0.4f * width, 0.15f * height, UNUSED,
            // p4
            1f * width, 0.95f * height, UNUSED,
            // p5
            0.9f * width, 1 * height, UNUSED
        };

        return retval;
    }
}
