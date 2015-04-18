package com.bitarcher.aeFun.drawables.animatedMeshed.Tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.geometry.pointsTransformation.SizeAdapterFunction;
import com.bitarcher.aeFun.geometry.primitives.BezierEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoid;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.geometry.pointsTransformation.IPointToPointFunction;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.list.SmartList;

import java.util.List;

/**
 * Created by michel on 17/04/15.
 */
public abstract class CompositeMeshesBase extends Entity {
    protected ISize paperModelSize;
    protected VertexBufferObjectManager vertexBufferObjectManager;

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


    /**
     *
     * @param meshColor
     * @param twoDPaperCoordinates ordered like TRIANGLE_STRIP
     * @return
     */
    protected Mesh getNewMesh(Color meshColor, List<IPoint> twoDPaperCoordinates)
    {
        Mesh retval =null;

        Pipeline pipeline = this.getNewPipeline();
        float[] transformedPoints = pipeline.applyAndGet3VfPoints(twoDPaperCoordinates);

        retval = new Mesh(0, 0, transformedPoints, transformedPoints.length / 3, DrawMode.TRIANGLE_STRIP, this.vertexBufferObjectManager);
        retval.setColor(meshColor);

        return retval;
    }

    protected BezierFilledEllipsoid getBezierFilledEllipsoid(Color meshColor, List<IPoint> twoDPaperCoordinates, IPoint ellipsoidTriangleFanCenterDesign, int numOfAdditionnalPointsBetweenPoints)
    {
        Pipeline pipeline = this.getNewPipeline();

        IPoint centerTransformed = pipeline.getYByX(ellipsoidTriangleFanCenterDesign);
        SmartList<IPoint> transformedList = pipeline.applyOnList(twoDPaperCoordinates);

        BezierFilledEllipsoid retval = new BezierFilledEllipsoid(0, 0, centerTransformed, transformedList, numOfAdditionnalPointsBetweenPoints, this.vertexBufferObjectManager);

        retval.setColor(meshColor);

        return retval;
    }

    protected BezierEllipsoid getBezierEllipsoid(Color meshColor, List<IPoint> twoDPaperCoordinates, int numOfAdditionnalPointsBetweenPoints)
    {
        Pipeline pipeline = this.getNewPipeline();

        SmartList<IPoint> transformedList = pipeline.applyOnList(twoDPaperCoordinates);

        BezierEllipsoid retval = new BezierEllipsoid(0, 0, transformedList, numOfAdditionnalPointsBetweenPoints, this.vertexBufferObjectManager);

        retval.setColor(meshColor);

        return retval;
    }
}
