package com.bitarcher.aeFun.drawables.animatedMeshed;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


import com.bitarcher.aeFun.drawables.animatedMeshed.Tools.CompositeMeshesBase;
import com.bitarcher.aeFun.drawables.animatedMeshed.Tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoidHelper;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.list.SmartList;

import java.util.ArrayList;

/**
 * Created by michel on 17/04/15.
 */
public class WindElasticTree1 extends WindElasticCompositeMeshes {

    Color rootColor = new Color(0.475f, 0.322f, 0.153f);
    Color leavesColorA = new Color(0.525f, 0.498f, 0.153f);
    Color leavesColorB;

    Mesh rootMesh;
    Mesh leavesAMesh;
    Mesh leavesBMesh;

    public Color getRootColor() {
        return rootColor;
    }

    public Color getLeavesColorA() {
        return leavesColorA;
    }


    public Color getLeavesColorB() {
        return leavesColorB;
    }


    public WindElasticTree1(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color rootColor, Color leavesColorA, Color leavesColorB) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(21, 54), windStrength, windSide);
        this.rootColor = rootColor;
        this.leavesColorA = leavesColorA;
        this.leavesColorB = leavesColorB;

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

        ArrayList<IPoint> rootPoints = new ArrayList<>();

        rootPoints.add(new Point(13, 0));
        rootPoints.add(new Point(8, 0));
        rootPoints.add(new Point(12, 16));
        rootPoints.add(new Point(8, 16));

        this.rootMesh = this.getNewMesh(this.rootColor, rootPoints);
        this.attachChild(this.rootMesh);

        ArrayList<IPoint> leavesAPoints = new ArrayList<>();

        /*
        leavesAPoints.add(new Point(12, 16)); //p0
        leavesAPoints.add(new Point(8, 16));  // p1
        leavesAPoints.add(new Point(21, 38)); // p2
        leavesAPoints.add(new Point(2, 21));  // p3
        leavesAPoints.add(new Point(20, 49));   // p4
        leavesAPoints.add(new Point(0, 34)); // P5
        leavesAPoints.add(new Point(15, 55)); //p6
        leavesAPoints.add(new Point(3, 47)); // p7
        leavesAPoints.add(new Point(9, 54));  // p8
        */


        leavesAPoints.add(new Point(8, 16));  // p1
        leavesAPoints.add(new Point(2, 21));  // p3
        leavesAPoints.add(new Point(0, 34)); // P5
        leavesAPoints.add(new Point(3, 47)); // p7
        leavesAPoints.add(new Point(9, 54));  // p8
        leavesAPoints.add(new Point(15, 55)); //p6
        leavesAPoints.add(new Point(20, 49));   // p4
        leavesAPoints.add(new Point(21, 38)); // p2
        leavesAPoints.add(new Point(12, 16)); //p0

/*
        leavesAPoints.add(new Point(0, 10));
        leavesAPoints.add(new Point(0, 50));
        leavesAPoints.add(new Point(10, 50));
        leavesAPoints.add(new Point(20, 50));
        leavesAPoints.add(new Point(20, 10));
*/
        Point designCenterPoint = new Point(10, 30);

        this.leavesAMesh = this.getNewMesh(this.leavesColorA, leavesAPoints);

        this.leavesAMesh = this.getBezierFilledEllipsoid(this.leavesColorA, leavesAPoints, designCenterPoint, 2);
        this.attachChild(this.leavesAMesh);

        Pipeline pipeline = this.getNewPipeline();

        IPoint centerTransformed = pipeline.getYByX(designCenterPoint);
        SmartList<IPoint> transformedList = pipeline.applyOnList(leavesAPoints);

        BezierFilledEllipsoidHelper bezierFilledEllipsoidHelper = new BezierFilledEllipsoidHelper(centerTransformed, transformedList, 5);
        SmartList<IPoint> bezierPoints = bezierFilledEllipsoidHelper.getBezierPoints();

        float[] fPoints = new float[3 * bezierPoints.size()];
        float UNUSED = 0;

        int i=0;
        for(IPoint iPoint : bezierPoints)
        {
            fPoints[i] = iPoint.getX();
            fPoints[i + 1] = iPoint.getY();
            fPoints[i + 2] = UNUSED;
            i+=3;
        }

        Mesh mesh = new Mesh(0, 0, fPoints, fPoints.length / 3, DrawMode.LINE_LOOP, this.vertexBufferObjectManager);
        mesh.setColor(Color.RED);


        this.attachChild(mesh);
    }
}
