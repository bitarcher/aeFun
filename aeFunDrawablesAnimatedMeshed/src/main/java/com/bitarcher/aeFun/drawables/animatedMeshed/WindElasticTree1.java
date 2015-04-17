package com.bitarcher.aeFun.drawables.animatedMeshed;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


import com.bitarcher.aeFun.drawables.animatedMeshed.Tools.CompositeMeshesBase;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/**
 * Created by michel on 17/04/15.
 */
public class WindElasticTree1 extends CompositeMeshesBase {

    Color rootColor;
    Color leavesColorA;
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


    public WindElasticTree1(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, Color rootColor, Color leavesColorA, Color leavesColorB) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(21, 54));
        this.rootColor = rootColor;
        this.leavesColorA = leavesColorA;
        this.leavesColorB = leavesColorB;
    }

    void computeMeshes()
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

        this.rootMesh = this.getNewMesh(new Color(0.475f, 0.322f, 0.153f), rootPoints);
        this.attachChild(this.rootMesh);
    }
}
