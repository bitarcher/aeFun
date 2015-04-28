package com.bitarcher.aeFun.drawables.animatedMeshed.nature.flowers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.colors.ColorSpaces;
import com.bitarcher.aeFun.colors.HSB;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.PositionAndSizeOwner;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.colors.IHSB;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.entity.primitive.Mesh;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/**
 * Created by michel on 26/04/15.
 */
public class WindElasticFlower0 extends WindElasticFlowerBase {

    Color petalColor = Color.RED;
    Color[] petalColors = new Color[8];
    ArrayList<BezierFilledEllipsoid> petalsArrayList = new ArrayList<>();
    Color middleDiskColor = Color.YELLOW;
    //DiskOrXGon middleDisk;
    Mesh middleDisk;

    void computePetalColors()
    {
        float brightnessDecrement = 0.05f;

        ColorSpaces colorSpaces = new ColorSpaces();
        IHSB origHsb = colorSpaces.color_to_HSB(this.petalColor);

        for(int i = 0; i < petalColors.length; i++)
        {
            float dec = brightnessDecrement * i;
            HSB hsb = new HSB(origHsb);
            hsb.setBrightness(origHsb.getBrightness() - dec);
            Color color = colorSpaces.HSB_to_Color(hsb);
            this.petalColors[i] = color;
        }
    }

    public WindElasticFlower0(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color stemColor, Color petalColor, Color middleDiskColor) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, windStrength, windSide, stemColor);
        this.petalColor = petalColor;
        this.middleDiskColor = middleDiskColor;

        this.computePetalColors();
        this.computeMeshes();
    }

    public WindElasticFlower0(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager);

        this.computePetalColors();
        this.computeMeshes();
    }

    public Color getPetalColor() {
        return petalColor;
    }


    @Override
    protected void computeMeshes() {
        super.computeMeshes();

        for(BezierFilledEllipsoid petal : this.petalsArrayList)
        {
            this.detachChild(petal);
        }

        if(this.middleDisk != null)
        {
            this.detachChild(this.middleDisk);
            this.middleDisk = null;
        }

        this.petalsArrayList.clear();

        ArrayList<ArrayList<IPoint>> pointsArrayArrayList = new ArrayList<>();

        ArrayList<IPoint> onePetalArrayList;



        // 0
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(316, 165));
        onePetalArrayList.add(new Point(354, 158));
        onePetalArrayList.add(new Point(393, 157));
        onePetalArrayList.add(new Point(422, 166));
        onePetalArrayList.add(new Point(430, 178));
        onePetalArrayList.add(new Point(426, 187));
        onePetalArrayList.add(new Point(387, 198));
        onePetalArrayList.add(new Point(354, 196));
        onePetalArrayList.add(new Point(318, 192));
        pointsArrayArrayList.add(onePetalArrayList);

        // 1
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(302, 146));
        onePetalArrayList.add(new Point(330, 122));
        onePetalArrayList.add(new Point(390, 88));
        onePetalArrayList.add(new Point(408, 86));
        onePetalArrayList.add(new Point(416, 110));
        onePetalArrayList.add(new Point(404, 124));
        onePetalArrayList.add(new Point(378, 138));
        onePetalArrayList.add(new Point(341, 154));
        onePetalArrayList.add(new Point(314, 162));
        pointsArrayArrayList.add(onePetalArrayList);

        // 2
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(276, 128));
        onePetalArrayList.add(new Point(302, 52));
        onePetalArrayList.add(new Point(319, 28));
        onePetalArrayList.add(new Point(338, 20));
        onePetalArrayList.add(new Point(354, 36));
        onePetalArrayList.add(new Point(354, 54));
        onePetalArrayList.add(new Point(344, 76));
        onePetalArrayList.add(new Point(331, 100));
        onePetalArrayList.add(new Point(302, 141));
        pointsArrayArrayList.add(onePetalArrayList);

        // 3
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(245, 128));
        onePetalArrayList.add(new Point(242, 58));
        onePetalArrayList.add(new Point(250, 17));
        onePetalArrayList.add(new Point(260, 3));
        onePetalArrayList.add(new Point(276, 0));
        onePetalArrayList.add(new Point(293, 6));
        onePetalArrayList.add(new Point(299, 27));
        onePetalArrayList.add(new Point(289, 75));
        onePetalArrayList.add(new Point(276, 128));
        pointsArrayArrayList.add(onePetalArrayList);


        // 4
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(218, 141));
        onePetalArrayList.add(new Point(180, 94));
        onePetalArrayList.add(new Point(166, 60));
        onePetalArrayList.add(new Point(164, 41));
        onePetalArrayList.add(new Point(170, 30));
        onePetalArrayList.add(new Point(184, 26));
        onePetalArrayList.add(new Point(206, 40));
        onePetalArrayList.add(new Point(224, 65));
        onePetalArrayList.add(new Point(243, 128));
        pointsArrayArrayList.add(onePetalArrayList);


        // 5
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(199,173));
        onePetalArrayList.add(new Point(146, 148));
        onePetalArrayList.add(new Point(116, 128));
        onePetalArrayList.add(new Point(108, 115));
        onePetalArrayList.add(new Point(104, 100));
        onePetalArrayList.add(new Point(108, 86));
        onePetalArrayList.add(new Point(140, 85));
        onePetalArrayList.add(new Point(170, 100));
        onePetalArrayList.add(new Point(200, 127));
        pointsArrayArrayList.add(onePetalArrayList);


        // 6
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(200, 202));
        onePetalArrayList.add(new Point(138, 204));
        onePetalArrayList.add(new Point(96, 198));
        onePetalArrayList.add(new Point(83, 190));
        onePetalArrayList.add(new Point(80, 176));
        onePetalArrayList.add(new Point(88, 163));
        onePetalArrayList.add(new Point(110, 158));
        onePetalArrayList.add(new Point(146, 161));
        onePetalArrayList.add(new Point(198, 174));
        pointsArrayArrayList.add(onePetalArrayList);

        // 7
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(219, 228));
        onePetalArrayList.add(new Point(182, 260));
        onePetalArrayList.add(new Point(144, 280));
        onePetalArrayList.add(new Point(124, 287));
        onePetalArrayList.add(new Point(106, 280));
        onePetalArrayList.add(new Point(104, 262));
        onePetalArrayList.add(new Point(116, 242));
        onePetalArrayList.add(new Point(148, 224));
        onePetalArrayList.add(new Point(201, 204));
        pointsArrayArrayList.add(onePetalArrayList);


        // 8
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(250, 240));
        onePetalArrayList.add(new Point(244, 286));
        onePetalArrayList.add(new Point(229, 326));
        onePetalArrayList.add(new Point(216, 340));
        onePetalArrayList.add(new Point(200, 346));
        onePetalArrayList.add(new Point(188, 332));
        onePetalArrayList.add(new Point(187, 301));
        onePetalArrayList.add(new Point(200, 268));
        onePetalArrayList.add(new Point(220, 230));
        pointsArrayArrayList.add(onePetalArrayList);


        // 9
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(280, 239));
        onePetalArrayList.add(new Point(284, 278));
        onePetalArrayList.add(new Point(284, 315));
        onePetalArrayList.add(new Point(282, 344));
        onePetalArrayList.add(new Point(270, 358));
        onePetalArrayList.add(new Point(252, 347));
        onePetalArrayList.add(new Point(248, 319));
        onePetalArrayList.add(new Point(248, 284));
        onePetalArrayList.add(new Point(250, 241));
        pointsArrayArrayList.add(onePetalArrayList);

        // 10
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(302, 224));
        onePetalArrayList.add(new Point(338, 274));
        onePetalArrayList.add(new Point(356, 310));
        onePetalArrayList.add(new Point(358, 327));
        onePetalArrayList.add(new Point(355, 339));
        onePetalArrayList.add(new Point(340, 340));
        onePetalArrayList.add(new Point(318, 324));
        onePetalArrayList.add(new Point(296, 284));
        onePetalArrayList.add(new Point(284, 234));
        pointsArrayArrayList.add(onePetalArrayList);

        // 11
        onePetalArrayList = new ArrayList<>();
        onePetalArrayList.add(new Point(319, 194));
        onePetalArrayList.add(new Point(365, 209));
        onePetalArrayList.add(new Point(410, 236));
        onePetalArrayList.add(new Point(414, 246));
        onePetalArrayList.add(new Point(411, 258));
        onePetalArrayList.add(new Point(392, 264));
        onePetalArrayList.add(new Point(362, 258));
        onePetalArrayList.add(new Point(333, 242));
        onePetalArrayList.add(new Point(303, 224));
        pointsArrayArrayList.add(onePetalArrayList);


        int numOfAdditionalPoints = 6;



        Point filledEllipsoidCenter = new Point(258, 184);

        int pointNum = 0;
        for(ArrayList<IPoint> points : pointsArrayArrayList)
        {
            int colorIndex = Math.abs(6 - pointNum) % this.petalColors.length;

            Color petalC = this.petalColors[colorIndex];
            BezierFilledEllipsoid ellipsoid = this.getBezierFilledEllipsoid(petalC, points, filledEllipsoidCenter, numOfAdditionalPoints);
            this.petalsArrayList.add(ellipsoid);
            this.attachChild(ellipsoid);

            pointNum++;
        }


        // middle disk

        this.middleDisk = this.getMeshedDisk(this.middleDiskColor, new PositionAndSizeOwner(filledEllipsoidCenter,
                new Size(52, 52)), 14);
        this.attachChild(this.middleDisk);
    }
}
