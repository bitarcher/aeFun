package com.bitarcher.aeFun.drawables.animatedMeshed;

import com.bitarcher.aeFun.drawables.animatedMeshed.Tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.geometry.primitives.BezierEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoid;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 17/04/15.
 * similar to a fir
 */
public class WindElasticTree2 extends WindElasticCompositeMeshes {

    Color rootColor;
    Color leftColor;
    Color rightColor;
    Color topColor;
    int numOfStages;
    float yIncrement;
    Mesh[] stagesMesh;

    Mesh rootMesh;
    Mesh topMesh;

    public int getNumOfStages() {
        return numOfStages;
    }

    public Color getRootColor() {
        return rootColor;
    }

    public Color getLeftColor() {
        return leftColor;
    }


    public Color getTopColor() {
        return topColor;
    }

    public Color getRightColor() {
        return rightColor;
    }

    public WindElasticTree2(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color rootColor, Color leftColor, Color rightColor, Color topColor, int numOfStages) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(20, 30), windStrength, windSide);
        this.rootColor = rootColor;
        this.leftColor = leftColor;
        this.rightColor = rightColor;
        this.topColor = topColor;
        this.numOfStages = numOfStages;
        this.stagesMesh = new Mesh[numOfStages * 2];

        if(numOfStages < 2)
        {
            throw new IllegalArgumentException("numOfStages < 2");
        }

        this.yIncrement = 27 / (numOfStages + 1); // one more stages for the top

        this.computeMeshes();
    }

    public WindElasticTree2(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        this(pX, pY, pWidth, pHeight, vertexBufferObjectManager, 0, EnumSide.Right,
                new Color(0.475f, 0.322f, 0.153f),
                new Color(0.525f, 0.498f, 0.153f),
                new Color(0.425f, 0.398f, 0.143f),
                new Color(0.5f, 0.8f, 0.4f),
                8
        );

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

        if(this.topMesh != null)
        {
            this.detachChild(this.topMesh);
            this.topMesh = null;
        }

        for(int i = 0 ; i < this.stagesMesh.length ; i++)
        {
            this.detachChild(this.stagesMesh[i]);
            this.stagesMesh[i] = null;
        }

        ArrayList<IPoint> rootPoints = new ArrayList<>();

        rootPoints.add(new Point(9, 0));
        rootPoints.add(new Point(11, 0));

        rootPoints.add(new Point(9, 3));
        rootPoints.add(new Point(11, 3));

        this.rootMesh = this.getNewMesh(this.rootColor, rootPoints);
        this.attachChild(this.rootMesh);

        this.stagesMesh = new Mesh[(this.numOfStages - 1) * 2];

        float currentY = 3;
        // last stage is for the top
        for(int i = 0; i < (this.numOfStages - 1); i++)
        {
            float nextY = currentY + this.yIncrement;

            Point p0 = new Point(this.outerLeftFiber(currentY), currentY);
            Point p1 = new Point(this.outerRightFiber(currentY), currentY);

            Point p2 = new Point(this.innerRightFiber(nextY), nextY);
            Point p3 = new Point(this.innerLeftFiber(nextY), nextY);

            ArrayList<IPoint> stageUpLeftPointsList = new ArrayList<>();
            stageUpLeftPointsList.add(p0);
            stageUpLeftPointsList.add(p2);
            stageUpLeftPointsList.add(p3);

            Color stageUpLeftColor = this.leftColor;
            Mesh stageUpLeftMesh = this.getNewMesh(stageUpLeftColor, stageUpLeftPointsList, DrawMode.TRIANGLES);
            this.attachChild(stageUpLeftMesh);
            this.stagesMesh[i * 2] = stageUpLeftMesh;

            ArrayList<IPoint> stageDownRightPointsList = new ArrayList<>();
            stageDownRightPointsList.add(p0);
            stageDownRightPointsList.add(p1);
            stageDownRightPointsList.add(p2);

            Color stageDownRightColor = this.rightColor;
            Mesh stageDownRightMesh = this.getNewMesh(stageDownRightColor, stageDownRightPointsList, DrawMode.TRIANGLES);
            this.attachChild(stageDownRightMesh);
            this.stagesMesh[i * 2 + 1] = stageDownRightMesh;


            currentY = nextY;
        }

        Point p0 = new Point(this.outerLeftFiber(currentY), currentY);
        Point p1 = new Point(this.outerRightFiber(currentY), currentY);

        Point p2 = new Point(10, 30);

        // top
        ArrayList<IPoint> topPointsList = new ArrayList<>();
        topPointsList.add(p0);
        topPointsList.add(p1);
        topPointsList.add(p2);


        this.topMesh = this.getNewMesh(this.topColor, topPointsList, DrawMode.TRIANGLES);
        this.attachChild(this.topMesh);
    }

    float outerLeftFiber(float y)
    {
        // y = 3x + 3  => x = (y - 3) / 3 = y/ 3 - 1

        return y / 3 - 1;
    }

    float innerLeftFiber(float y)
    {
        // y = 4x - 12 => x = (y + 12) / 4 = y / 4 + 3
        return y / 4 + 3;
    }

    float innerRightFiber(float y)
    {
        // y = -4x + 68 => y - 68 = -4x => -y + 68 = 4x => x = -y / 4 + 17
        return - y / 4 + 17;
    }

    float outerRightFiber(float y)
    {
        // y = -3x + 63 =>  y - 63 = -3x => x = -y/3 + 21
        return -y/3 + 21;
    }

}

