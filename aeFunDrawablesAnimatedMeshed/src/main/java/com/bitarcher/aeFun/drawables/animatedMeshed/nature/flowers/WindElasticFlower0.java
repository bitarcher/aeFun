package com.bitarcher.aeFun.drawables.animatedMeshed.nature.flowers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.EnumSide;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 26/04/15.
 */
public class WindElasticFlower0 extends WindElasticFlowerBase {
    public WindElasticFlower0(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color stemColor) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, windStrength, windSide, stemColor);
    }

    public WindElasticFlower0(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager);
    }
}
