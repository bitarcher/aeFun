package com.bitarcher.aeFun.geometry.primitives;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.primitives.helpers.BezierEllipsoidHelper;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.list.SmartList;

/**
 * Created by michel on 17/04/15.
 */
public class BezierFilledEllipsoid extends Entity {

    IPoint ellipsoidTriangleFanCenter;
    SmartList<IPoint> points;
    int numOfAdditionalPointsBetweenPoints;
    VertexBufferObjectManager vertexBufferObjectManager;
    Mesh mesh;

    public BezierFilledEllipsoid(float pX, float pY, IPoint ellipsoidTriangleFanCenter, SmartList<IPoint> points, int numOfAdditionalPointsBetweenPoints, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY);
        this.ellipsoidTriangleFanCenter = ellipsoidTriangleFanCenter;
        this.points = points;
        this.numOfAdditionalPointsBetweenPoints = numOfAdditionalPointsBetweenPoints;
        this.vertexBufferObjectManager = vertexBufferObjectManager;

        BezierEllipsoidHelper helper = new BezierEllipsoidHelper(ellipsoidTriangleFanCenter, points, numOfAdditionalPointsBetweenPoints);
        float[] fanPoints = helper.computeBufferDataForFan();

        int vertexCount = fanPoints.length / 3;

        this.mesh = new Mesh(0, 0, fanPoints, vertexCount, DrawMode.TRIANGLE_FAN, vertexBufferObjectManager);

        this.attachChild(this.mesh);
    }

    @Override
    public void setColor(Color pColor) {
        super.setColor(pColor);

        this.mesh.setColor(pColor);
    }
}
