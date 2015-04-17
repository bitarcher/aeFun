package com.bitarcher.aeFun.drawables.animatedMeshed.Tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.geometry.pointsTransformation.SizeAdapterFunction;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.geometry.pointsTransformation.IPointToPointFunction;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.List;

/**
 * Created by michel on 17/04/15.
 */
public abstract class CompositeMeshesBase extends Entity {
    ISize paperModelSize;
    VertexBufferObjectManager vertexBufferObjectManager;

    protected CompositeMeshesBase(VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize) {
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.paperModelSize = paperModelSize;
    }

    protected CompositeMeshesBase(float pX, float pY, VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize) {
        super(pX, pY);
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.paperModelSize = paperModelSize;
    }

    protected CompositeMeshesBase(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, ISize paperModelSize) {
        super(pX, pY, pWidth, pHeight);

        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.paperModelSize = paperModelSize;
    }

    protected SizeAdapterFunction getNewPaperToOutputSizeAdapterFunction()
    {
        SizeAdapterFunction retval = new SizeAdapterFunction(this.paperModelSize, new Size(this.getWidth(), this.getHeight()));

        return retval;
    }

    protected Pipeline getNewPipeline()
    {
        Pipeline retval = new Pipeline();

        retval.appendFunction(this.getNewPaperToOutputSizeAdapterFunction());

        return retval;
    }

    protected Mesh getNewMesh(Color meshColor, List<IPoint> twoDPaperCoordinates)
    {
        Mesh retval =null;

        Pipeline pipeline = this.getNewPipeline();
        float[] transformedPoints = pipeline.applyAndGet3VfPoints(twoDPaperCoordinates);

        retval = new Mesh(0, 0, transformedPoints, transformedPoints.length / 3, DrawMode.TRIANGLE_STRIP, this.vertexBufferObjectManager);
        retval.setColor(meshColor);

        return retval;
    }
}
