package com.bitarcher.aeFun.drawables.animatedMeshed.nature.flowers;

import com.bitarcher.aeFun.colors.ColorSpaces;
import com.bitarcher.aeFun.colors.HSB;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.colors.IHSB;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

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
 * Created by michel on 26/04/15.
 */
public class WindElasticFlower1 extends WindElasticFlowerBase {

    Color petalColor = Color.RED;
    BezierFilledEllipsoid petalsEllipsoid;
    Mesh petalTriangle;



    public WindElasticFlower1(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color stemColor, Color petalColor) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, windStrength, windSide, stemColor);
        this.petalColor = petalColor;

        this.computeMeshes();
    }

    public WindElasticFlower1(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager);

        this.computeMeshes();
    }

    public Color getPetalColor() {
        return petalColor;
    }


    @Override
    protected void computeMeshes() {
        super.computeMeshes();

        if(this.petalsEllipsoid != null)
        {
            this.detachChild(this.petalsEllipsoid);
            this.petalsEllipsoid = null;
        }

        if(this.petalTriangle != null)
        {
            this.detachChild(this.petalTriangle);
            this.petalTriangle = null;
        }


        ArrayList<IPoint> onePetalArrayList;

        Point backPoint = new Point(240, 173);

        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(backPoint);
        onePetalArrayList.add(new Point(172, 93));
        onePetalArrayList.add(new Point(172, 93));
        onePetalArrayList.add(new Point(146, 120));
        onePetalArrayList.add(new Point(131, 151));
        onePetalArrayList.add(new Point(126, 175));
        onePetalArrayList.add(new Point(127, 212));
        onePetalArrayList.add(new Point(144, 259));
        onePetalArrayList.add(new Point(171, 290));
        onePetalArrayList.add(new Point(200, 307));
        onePetalArrayList.add(new Point(228, 314));
        onePetalArrayList.add(new Point(274, 310));
        onePetalArrayList.add(new Point(305, 293));
        onePetalArrayList.add(new Point(323, 276));
        onePetalArrayList.add(new Point(341, 250));
        onePetalArrayList.add(new Point(351, 223));
        onePetalArrayList.add(new Point(354, 200));
        onePetalArrayList.add(new Point(353, 177));
        onePetalArrayList.add(new Point(349, 156));
        onePetalArrayList.add(new Point(343, 137));
        onePetalArrayList.add(new Point(335, 123));
        onePetalArrayList.add(new Point(330, 115));
        onePetalArrayList.add(new Point(330, 115));
        onePetalArrayList.add(backPoint);



        int numOfAdditionalPoints = 6;



        Point filledEllipsoidCenter = new Point(235, 220);



        this.petalsEllipsoid = this.getBezierFilledEllipsoid(this.petalColor, onePetalArrayList, filledEllipsoidCenter, numOfAdditionalPoints);

        this.attachChild(this.petalsEllipsoid);


        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(166, 194));
        onePetalArrayList.add(new Point(310, 208));
        onePetalArrayList.add(new Point(251, 100));

        this.petalTriangle = this.getNewMesh(this.petalColor, onePetalArrayList);
        this.attachChild(this.petalTriangle);
    }
}
