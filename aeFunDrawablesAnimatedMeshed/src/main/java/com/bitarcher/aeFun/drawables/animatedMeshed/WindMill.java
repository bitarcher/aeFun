package com.bitarcher.aeFun.drawables.animatedMeshed;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.animatedMeshed.Tools.CompositeMeshesBase;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

import org.andengine.entity.Entity;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 18/04/15.
 */
public class WindMill extends CompositeMeshesBase {

    Color stonesGradientColor1 = new Color(0.9f, 0.85f, 0.8f);
    Color stonesGradientColor2 = new Color(0.8f, 0.75f, 0.7f);
    Color roofColor = new Color(0.95f, 0.4f, 0.45f);
    Color bladesColor = new Color(0.804f, 0.522f, 0.247f); // peru beige


    public WindMill(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(30, 30));
    }


}
