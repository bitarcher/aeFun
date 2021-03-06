package com.bitarcher.aeFun.drawables.animatedMeshed.nature.trees;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


import com.bitarcher.aeFun.drawables.animatedMeshed.tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.primitives.BezierEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoid;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/**
 * Created by michel on 17/04/15.
 */
public class WindElasticTree1 extends WindElasticCompositeMeshes {

    int numOfBezierAdditionalPoints = 25;
    Color rootColor = new Color(0.475f, 0.322f, 0.153f);
    Color leavesColorA = new Color(0.525f, 0.498f, 0.153f);
    Color leavesColorABorder = new Color(0.425f, 0.398f, 0.143f);
    Color leavesColorB = new Color(0.5f, 0.8f, 0.4f);

    Mesh rootMesh;
    BezierFilledEllipsoid leavesAMesh;
    BezierEllipsoid leavesABorder;
    BezierFilledEllipsoid leavesBMesh;

    public Color getRootColor() {
        return rootColor;
    }

    public Color getLeavesColorA() {
        return leavesColorA;
    }


    public Color getLeavesColorB() {
        return leavesColorB;
    }


    public WindElasticTree1(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color rootColor, Color leavesColorA, Color leavesColorABorder, Color leavesColorB, int numOfBezierAdditionalPoints) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(21, 54), windStrength, windSide);
        this.rootColor = rootColor;
        this.leavesColorA = leavesColorA;
        this.leavesColorABorder = leavesColorABorder;
        this.leavesColorB = leavesColorB;
        this.numOfBezierAdditionalPoints = numOfBezierAdditionalPoints;

        this.computeMeshes();
    }


    public WindElasticTree1(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(21, 54), 0, EnumSide.Right);

        this.computeMeshes();
    }


    @Override
    protected void computeMeshes()
    {
        if(this.rootMesh != null)
        {
            this.detachChild(this.rootMesh);
            this.rootMesh = null;
        }

        if(this.leavesAMesh != null)
        {
            this.detachChild(this.leavesAMesh);
            this.leavesAMesh = null;
        }

        if(this.leavesBMesh != null)
        {
            this.detachChild(this.leavesBMesh);
            this.leavesBMesh = null;
        }

        if(this.leavesABorder != null)
        {
            this.detachChild(this.leavesABorder);
            this.leavesABorder = null;
        }

        ArrayList<IPoint> rootPoints = new ArrayList<>();

        rootPoints.add(new Point(13, 0));
        rootPoints.add(new Point(8, 0));
        rootPoints.add(new Point(12, 16));
        rootPoints.add(new Point(8, 16));

        this.rootMesh = this.getNewMesh(this.rootColor, rootPoints);
        this.attachChild(this.rootMesh);

        ArrayList<IPoint> leavesAPoints = new ArrayList<>();

        leavesAPoints.add(new Point(8, 16));  // p1
        leavesAPoints.add(new Point(2, 21));  // p3
        leavesAPoints.add(new Point(0, 34)); // P5
        leavesAPoints.add(new Point(3, 47)); // p7
        leavesAPoints.add(new Point(9, 54));  // p8
        leavesAPoints.add(new Point(15, 55)); //p6
        leavesAPoints.add(new Point(20, 49));   // p4
        leavesAPoints.add(new Point(21, 38)); // p2
        leavesAPoints.add(new Point(20, 31));
        leavesAPoints.add(new Point(16, 25));
        leavesAPoints.add(new Point(12, 14)); //p0
        leavesAPoints.add(new Point(10, 15));

        Point designCenterPoint = new Point(10, 30);

        this.leavesAMesh = this.getBezierFilledEllipsoid(this.leavesColorA, leavesAPoints, designCenterPoint, this.numOfBezierAdditionalPoints);
        this.attachChild(this.leavesAMesh);

        this.leavesABorder = this.getNewBezierEllipsoid(this.leavesColorABorder, leavesAPoints,  this.numOfBezierAdditionalPoints);
        this.attachChild(this.leavesABorder);

        float lineWidth = (float)(Math.sqrt(this.getWidth() * this.getHeight()) / 25.0);
        this.leavesABorder.setLineWidth(lineWidth);


        ArrayList<IPoint> leavesBPoints = new ArrayList<>();

        leavesBPoints.add(new Point(11, 18));
        leavesBPoints.add(new Point(10, 20));
        leavesBPoints.add(new Point(9.5f, 26));
        leavesBPoints.add(new Point(10, 30));
        leavesBPoints.add(new Point(11, 32));
        leavesBPoints.add(new Point(12, 42));
        leavesBPoints.add(new Point(13, 45));
        leavesBPoints.add(new Point(14, 47));
        leavesBPoints.add(new Point(15, 48));
        leavesBPoints.add(new Point(18, 46));
        leavesBPoints.add(new Point(19, 38));
        leavesBPoints.add(new Point(18, 34));
        leavesBPoints.add(new Point(16, 28));
        leavesBPoints.add(new Point(13, 21));
        leavesBPoints.add(new Point(12, 19));

        this.leavesBMesh = this.getBezierFilledEllipsoid(this.leavesColorB, leavesBPoints, new Point(13, 34),  (int)(this.numOfBezierAdditionalPoints / 2) + 1);
        this.attachChild(this.leavesBMesh);
    }
}
