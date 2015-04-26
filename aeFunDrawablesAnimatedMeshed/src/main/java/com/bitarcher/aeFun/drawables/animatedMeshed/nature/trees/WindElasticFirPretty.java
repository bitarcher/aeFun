package com.bitarcher.aeFun.drawables.animatedMeshed.nature.trees;

import com.bitarcher.aeFun.colors.GradientMaker;
import com.bitarcher.aeFun.geometry.Point;
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
 * Created by michel on 24/04/15.
 */
public class WindElasticFirPretty extends WindElasticFirBase {
    float yIncrement2;

    public WindElasticFirPretty(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color rootColor, Color leftColor, Color rightColor, Color topColor, int numOfStages) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, windStrength, windSide, rootColor, leftColor, rightColor, topColor, numOfStages);
        this.yIncrement2 = this.yIncrement / 2;
    }

    public WindElasticFirPretty(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager);
        this.yIncrement2 = this.yIncrement / 2;
    }

    @Override
    protected ArrayList<IPoint> getRootPoints() {

        ArrayList<IPoint> retval = new ArrayList<>();

        retval.add(new Point(9, 0));
        retval.add(new Point(11, 0));
        retval.add(new Point(9, 3));
        retval.add(new Point(11, 3));
        retval.add(new Point(9.5f, 15));
        retval.add(new Point(10.5f, 15));
        retval.add(new Point(9.8f, 25));
        retval.add(new Point(10.2f, 25));
        retval.add(new Point(10f, 30));
        retval.add(new Point(10f, 30));


        return retval;
    }

    @Override
    protected void customiseLeftAndRightMesh(Mesh leftMesh, Mesh righMesh) {
        leftMesh.setAlpha(0.7f);
        righMesh.setAlpha(0.7f);
    }

    @Override
    protected void customiseTopMesh(Mesh topMesh) {
        topMesh.setAlpha(0.7f);
    }

    @Override
    protected void addAdditionalStagesMesh(Color stageUpLeftColor, Color stageDownRightColor, float nextY, float currentY, IPoint p0, IPoint p1, IPoint p2, IPoint p3) {
        GradientMaker gradientMaker = new GradientMaker(stageUpLeftColor, stageDownRightColor);

        float y2 = currentY - this.yIncrement2;
        Point pY2L = new Point(this.innerLeftFiber(y2), y2);
        Point pY2R = new Point(this.innerRightFiber(y2), y2);

        Color lc = gradientMaker.getColor(0.3f);
        Color rc = gradientMaker.getColor(0.7f);

        ArrayList<IPoint> lmPoints = new ArrayList<>();
        lmPoints.add(p2);
        lmPoints.add(p3);
        lmPoints.add(pY2L);

        Mesh lm = this.getNewMesh(lc, lmPoints, DrawMode.TRIANGLES);
        lm.setAlpha(0.8f);

        ArrayList<IPoint> rmPoints = new ArrayList<>();
        rmPoints.add(p2);
        rmPoints.add(p3);
        rmPoints.add(pY2R);

        Mesh rm = this.getNewMesh(rc, rmPoints, DrawMode.TRIANGLES);
        rm.setAlpha(0.8f);

        this.attachChild(lm);
        this.stagesMeshArrayList.add(lm);

        this.attachChild(rm);
        this.stagesMeshArrayList.add(rm);

        //Mesh lm = this.getN
    }
}
