package com.bitarcher.aeFun.geometry.primitives;

import com.bitarcher.aeFun.geometry.primitives.Helpers.BezierEllipsoidHelper;
import com.bitarcher.aeFun.geometry.primitives.nazgees.TexturedMesh;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;

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
public class BezierTexturedEllipsoid extends Entity {

    IPoint ellipsoidTriangleFanCenter;
    SmartList<IPoint> points;
    int numOfAdditionalPointsBetweenPoints;
    VertexBufferObjectManager vertexBufferObjectManager;
    //eu.nazgee.sunflower.primitives.
    TexturedMesh texturedMesh;

    public BezierTexturedEllipsoid(float pX, float pY, IPoint ellipsoidTriangleFanCenter, SmartList<IPoint> points, int numOfAdditionalPointsBetweenPoints, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY);
        this.ellipsoidTriangleFanCenter = ellipsoidTriangleFanCenter;
        this.points = points;
        this.numOfAdditionalPointsBetweenPoints = numOfAdditionalPointsBetweenPoints;
        this.vertexBufferObjectManager = vertexBufferObjectManager;

        BezierEllipsoidHelper helper = new BezierEllipsoidHelper(ellipsoidTriangleFanCenter, points, numOfAdditionalPointsBetweenPoints);
        float[] fanPoints = helper.computeBufferDataForFan();

        int vertexCount = fanPoints.length / 3;

        //this.texturedMesh = new TexturedMesh(0, 0, fanPoints, vertexCount, DrawMode.TRIANGLE_FAN, vertexBufferObjectManager);
        //this.attachChild(this.texturedMesh);
    }
}
