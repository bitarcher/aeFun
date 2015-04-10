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
        super(x, y, CheckSymbol.getCoordinates(width, height), 6, DrawMode.TRIANGLE_STRIP, pVertexBufferObjectManager);


        // blue by default, but the color has to be set by the container
        this.setColor(0, 0, 1);
    }

    static float[] getCoordinates(float width, float height)
    {
        float UNUSED = 0;

        float w2 = width/2;
        float h2 = height/2;

        // coordinates for triangle_strip
        float[] retval = new float[]{
            // p0
            0.05f * width - w2, 0.6f * height - h2, UNUSED,
            // p1
            0 - w2, 0.5f * height - h2, UNUSED,
            // p2
            0.4f * width - w2, 0 - h2, UNUSED,
            // p3
            0.4f * width - w2, 0.15f * height - h2, UNUSED,
            // p4
            1f * width - w2, 0.95f * height - h2, UNUSED,
            // p5
            0.9f * width - w2, 1 * height - h2, UNUSED
        };

        return retval;
    }
}
