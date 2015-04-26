package com.bitarcher.aeFun.drawables.animatedMeshed.nature.trees;

import com.bitarcher.aeFun.colors.GradientMaker;
import com.bitarcher.aeFun.drawables.animatedMeshed.tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
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
public abstract class WindElasticFirBase extends WindElasticCompositeMeshes {

    Color rootColor;
    Color leftColor;
    Color rightColor;
    Color topColor;
    int numOfStages;
    float yIncrement;
    ArrayList<Mesh> stagesMeshArrayList;

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

    public WindElasticFirBase(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color rootColor, Color leftColor, Color rightColor, Color topColor, int numOfStages) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(20, 30), windStrength, windSide);
        this.rootColor = rootColor;
        this.leftColor = leftColor;
        this.rightColor = rightColor;
        this.topColor = topColor;
        this.numOfStages = numOfStages;
        this.stagesMeshArrayList = new ArrayList<>();

        if(numOfStages < 2)
        {
            throw new IllegalArgumentException("numOfStages < 2");
        }

        this.yIncrement = 27 / (numOfStages + 1); // one more stages for the top

        this.computeMeshes();
    }

    public WindElasticFirBase(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        this(pX, pY, pWidth, pHeight, vertexBufferObjectManager, 0, EnumSide.Right,
                new Color(0.475f, 0.322f, 0.153f),
                new Color(0.525f, 0.498f, 0.153f),
                new Color(0.425f, 0.398f, 0.143f),
                new Color(0.5f, 0.8f, 0.4f),
                8
        );

        this.computeMeshes();
    }

    protected void customiseLeftAndRightMesh(Mesh leftMesh, Mesh righMesh)
    {

    }

    protected void customiseTopMesh(Mesh topMesh)
    {

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

        for(Mesh stagesMesh : this.stagesMeshArrayList)
        {
            this.detachChild(stagesMesh);
        }

        this.stagesMeshArrayList.clear();



        ArrayList<IPoint>  rootPoints = this.getRootPoints();

        this.rootMesh = this.getNewMesh(this.rootColor, rootPoints);
        this.attachChild(this.rootMesh);

        float colorDistanceIncrement = 1f / (this.numOfStages - 1);
        float currentColorDistance = 0;

        GradientMaker leftGradientMaker = new GradientMaker(this.leftColor, this.topColor);
        GradientMaker rightGradientMaker = new GradientMaker(this.rightColor, this.topColor);

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

            Color stageUpLeftColor = leftGradientMaker.getColor(currentColorDistance);
            Mesh stageUpLeftMesh = this.getNewMesh(stageUpLeftColor, stageUpLeftPointsList, DrawMode.TRIANGLES);
            this.attachChild(stageUpLeftMesh);
            this.stagesMeshArrayList.add(stageUpLeftMesh);

            ArrayList<IPoint> stageDownRightPointsList = new ArrayList<>();
            stageDownRightPointsList.add(p0);
            stageDownRightPointsList.add(p1);
            stageDownRightPointsList.add(p2);

            Color stageDownRightColor = rightGradientMaker.getColor(currentColorDistance);
            Mesh stageDownRightMesh = this.getNewMesh(stageDownRightColor, stageDownRightPointsList, DrawMode.TRIANGLES);
            this.attachChild(stageDownRightMesh);
            this.stagesMeshArrayList.add(stageDownRightMesh);

            this.customiseLeftAndRightMesh(stageUpLeftMesh, stageDownRightMesh);
            this.addAdditionalStagesMesh(stageUpLeftColor, stageDownRightColor, nextY, currentY, p0, p1, p2, p3);


            currentY = nextY;
            currentColorDistance += colorDistanceIncrement;
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

        this.customiseTopMesh(this.topMesh);
    }

    protected void addAdditionalStagesMesh(Color stageUpLeftColor, Color stageDownRightColor, float nextY, float currentY, IPoint p0, IPoint p1, IPoint p2, IPoint p3)
    {
    }

    protected abstract ArrayList<IPoint> getRootPoints();

    protected float outerLeftFiber(float y)
    {
        // y = 3x + 3  => x = (y - 3) / 3 = y/ 3 - 1

        return y / 3 - 1;
    }

    protected float innerLeftFiber(float y)
    {
        // y = 4x - 12 => x = (y + 12) / 4 = y / 4 + 3
        return y / 4 + 3;
    }

    protected float innerRightFiber(float y)
    {
        // y = -4x + 68 => y - 68 = -4x => -y + 68 = 4x => x = -y / 4 + 17
        return - y / 4 + 17;
    }

    protected float outerRightFiber(float y)
    {
        // y = -3x + 63 =>  y - 63 = -3x => x = -y/3 + 21
        return -y/3 + 21;
    }

}

