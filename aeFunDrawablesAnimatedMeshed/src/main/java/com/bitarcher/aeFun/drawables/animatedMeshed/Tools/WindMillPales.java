package com.bitarcher.aeFun.drawables.animatedMeshed.Tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.geometry.pointsTransformation.RotationFunction;
import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.drawables.animatedMeshed.IWindStrength;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;
import com.bitarcher.aeFun.interfaces.geometry.ISize;

import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/**
 * Created by michel on 19/04/15.
 */
public class WindMillPales extends CompositeMeshesBase  implements IWindStrength {
    float windStrength = 0;
    Color bladesColor;
    float angle = 0;
    Mesh[] pales = new Mesh[4];

    DiskOrXGon diskOrXGon;

    public WindMillPales(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, Color bladesColor) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(20, 20));
        this.bladesColor = bladesColor;

        this.computeMeshes();
    }

    void computeMeshes()
    {
        if(this.diskOrXGon != null)
        {
            this.detachChild(this.diskOrXGon);
            this.diskOrXGon = null;
        }

        for(int i = 0 ; i < 4 ; i++)
        {
            if(this.pales[i] != null)
            {
                this.detachChild(this.pales[i]);
                this.pales[i] = null;
            }
        }

        this.diskOrXGon = this.getNewDiskOrXGon(this.bladesColor, 10, 10, 4, 4, 12);
        this.attachChild(this.diskOrXGon);

        ArrayList<IPoint> p1Points = new ArrayList<>();
        p1Points.add(new Point(10, 10));
        p1Points.add(new Point(20, 5));
        p1Points.add(new Point(20, 15));

        this.pales[0] = this.getNewMesh(this.bladesColor, p1Points);
        this.attachChild(this.pales[0]);

        ArrayList<IPoint> p2Points = new ArrayList<>();
        p2Points.add(new Point(10, 10));
        p2Points.add(new Point(15, 20));
        p2Points.add(new Point(5, 20));

        this.pales[1] = this.getNewMesh(this.bladesColor, p2Points);
        this.attachChild(this.pales[1]);

        ArrayList<IPoint> p3Points = new ArrayList<>();
        p3Points.add(new Point(10, 10));
        p3Points.add(new Point(0, 5));
        p3Points.add(new Point(0, 15));


        this.pales[2] = this.getNewMesh(this.bladesColor, p3Points);
        this.attachChild(this.pales[2]);

        ArrayList<IPoint> p4Points = new ArrayList<>();
        p4Points.add(new Point(10, 10));
        p4Points.add(new Point(15, 0));
        p4Points.add(new Point(5, 0));

        this.pales[3] = this.getNewMesh(this.bladesColor, p4Points);
        this.attachChild(this.pales[3]);

    }

    @Override
    public float getWindStrength() {
        return windStrength;
    }

    @Override
    public void setWindStrength(float windStrength) {

        this.windStrength = windStrength;
        this.computeMeshes();
    }

    RotationFunction getNewRotationFunction()
    {
        return new RotationFunction(new Point(10, 10), this.angle);
    }

    @Override
    protected Pipeline getNewPipeline() {

        Pipeline retval = super.getNewPipeline();

        //retval = new Pipeline();
        retval.prependFunction(this.getNewRotationFunction());

        return retval;
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.angle += pSecondsElapsed * this.windStrength;
        this.computeMeshes();
    }
}
