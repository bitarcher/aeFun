package com.bitarcher.aeFun.geometry.primitives;

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.list.SmartList;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 17/04/15.
 */
public class BezierEllipsoid extends Entity {

    IPoint ellipsoidTriangleFanCenter;
    SmartList<IPoint> points;
    int numOfAdditionalPointsBetweenPoints;
    VertexBufferObjectManager vertexBufferObjectManager;
    Mesh mesh;
    float lineWidth = 0.5f;

    public BezierEllipsoid(float pX, float pY, SmartList<IPoint> points, int numOfAdditionalPointsBetweenPoints, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY);
        this.points = points;
        this.numOfAdditionalPointsBetweenPoints = numOfAdditionalPointsBetweenPoints;
        this.vertexBufferObjectManager = vertexBufferObjectManager;

        BezierFilledEllipsoidHelper helper = new BezierFilledEllipsoidHelper(new Point(0, 0) /* not used */, points, numOfAdditionalPointsBetweenPoints);
        float[] fanPoints = helper.computeBufferDataForLineLoop();

        int vertexCount = fanPoints.length / 3;

        this.mesh = new Mesh(0, 0, fanPoints, vertexCount, DrawMode.LINE_LOOP, vertexBufferObjectManager);

        this.attachChild(this.mesh);
    }

    @Override
    public void setColor(Color pColor) {
        super.setColor(pColor);

        this.mesh.setColor(pColor);


    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    @Override
    protected void postDraw(GLState pGLState, Camera pCamera) {
        super.postDraw(pGLState, pCamera);

        pGLState.lineWidth(this.lineWidth);
    }

}
