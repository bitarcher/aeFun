package com.bitarcher.aeFun.drawables.animatedMeshed.Tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.drawables.animatedMeshed.IWindStrength;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 19/04/15.
 */
public class WindMillPales extends CompositeMeshesBase  implements IWindStrength {
    float windStrength = 0;
    Color bladesColor;

    DiskOrXGon diskOrXGon;

    public WindMillPales(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, Color bladesColor) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(20, 20));
        this.bladesColor = bladesColor;

        this.computeMeshes();
    }

    void computeMeshes()
    {
        if(this.diskOrXGon != null)
        {
            this.detachChild(this.diskOrXGon);
            this.diskOrXGon = null;
        }

        this.diskOrXGon = this.getNewDiskOrXGon(this.bladesColor, 10, 10, 4, 4, 12);
        this.attachChild(this.diskOrXGon);
    }

    @Override
    public float getWindStrength() {
        return windStrength;
    }

    @Override
    public void setWindStrength(float windStrength) {

        this.windStrength = windStrength;
        this.computeMeshes();
    }

}
