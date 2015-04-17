package com.bitarcher.aeFun.drawables.animatedMeshed.Tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by michel on 17/04/15.
 */
public class WindElasticCompositeMeshes extends CompositeMeshesBase {
    float windStrength = 0;
    EnumSide windSide = EnumSide.Right;

    public float getWindStrength() {
        return windStrength;
    }

    public void setWindStrength(float windStrength) {
        this.windStrength = windStrength;
    }

    public EnumSide getWindSide() {
        return windSide;
    }

    public void setWindSide(EnumSide windSide) {
        this.windSide = windSide;
    }

    public WindElasticCompositeMeshes(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize, float windStrength, EnumSide windSide) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, paperModelSize);
        this.windStrength = windStrength;
        this.windSide = windSide;
    }
}
