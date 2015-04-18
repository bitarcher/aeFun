package com.bitarcher.aeFun.drawables.animatedMeshed.Tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.geometry.pointsTransformation.WindFunction;
import com.bitarcher.aeFun.interfaces.drawables.animatedMeshed.IWindStrength;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by michel on 17/04/15.
 */
public abstract class WindElasticCompositeMeshes extends CompositeMeshesBase implements IWindStrength {
    float windStrength = 0;
    EnumSide windSide = EnumSide.Right;

    @Override
    public float getWindStrength() {
        return windStrength;
    }

    @Override
    public void setWindStrength(float windStrength) {

        this.windStrength = windStrength;
        this.computeMeshes();
    }

    public EnumSide getWindSide() {
        return windSide;
    }

    public void setWindSide(EnumSide windSide) {

        this.windSide = windSide;
        this.computeMeshes();
    }

    public WindElasticCompositeMeshes(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize, float windStrength, EnumSide windSide) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, paperModelSize);
        this.windStrength = windStrength;
        this.windSide = windSide;
    }

    protected abstract void computeMeshes();

    protected WindFunction getNewWindFunction()
    {
        WindFunction windFunction = new WindFunction(this.windStrength, this.windSide, new Size(this.getWidth(), this.getHeight()));

        return windFunction;
    }

    @Override
    protected Pipeline getNewPipeline()
    {
        Pipeline retval = new Pipeline();

        retval.appendFunction(this.getNewPaperToOutputSizeAdapterFunction());
        retval.appendFunction(this.getNewWindFunction());

        return retval;
    }

}
