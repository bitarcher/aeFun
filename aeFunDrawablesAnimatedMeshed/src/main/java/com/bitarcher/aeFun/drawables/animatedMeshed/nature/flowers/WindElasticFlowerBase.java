package com.bitarcher.aeFun.drawables.animatedMeshed.nature.flowers;

import com.bitarcher.aeFun.colors.ColorSpaces;
import com.bitarcher.aeFun.colors.HSB;
import com.bitarcher.aeFun.drawables.animatedMeshed.tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.geometry.Size;
import com.bitarcher.aeFun.geometry.pointsTransformation.FlipYAxisFunction;
import com.bitarcher.aeFun.geometry.pointsTransformation.Pipeline;
import com.bitarcher.aeFun.geometry.primitives.BezierEllipsoid;
import com.bitarcher.aeFun.geometry.primitives.BezierFilledEllipsoid;
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
 * Created by michel on 17/04/15.
 */
public class WindElasticFlowerBase extends WindElasticCompositeMeshes {

    Color stemColor;
    Color colorRight;
    Color colorLeft;

    Mesh stemMesh;
    Mesh leaveRightMesh;
    Mesh leaveLeftMesh;

    public Color getStemColor() {
        return this.stemColor;
    }

    public WindElasticFlowerBase(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color stemColor) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, new Size(441, 730), windStrength, windSide);
        this.stemColor = stemColor;

        ColorSpaces colorSpaces = new ColorSpaces();
        IHSB stemHsb = colorSpaces.color_to_HSB(this.stemColor);

        HSB hsbRight = new HSB(stemHsb);
        hsbRight.setBrightness(stemHsb.getBrightness() - 0.1f);
        this.colorRight = colorSpaces.HSB_to_Color(hsbRight);

        HSB hsbLeft = new HSB(stemHsb);
        hsbLeft.setBrightness(stemHsb.getBrightness() + 0.1f);
        this.colorLeft = colorSpaces.HSB_to_Color(hsbLeft);

        this.computeMeshes();
    }


    public WindElasticFlowerBase(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        this(pX, pY, pWidth, pHeight, vertexBufferObjectManager, 0, EnumSide.Right, new Color(51f / 255f, 198f / 255f, 8f / 255f));
    }

    @Override
    protected Pipeline getNewPipeline() {
        Pipeline retval = super.getNewPipeline();

        retval.prependFunction(new FlipYAxisFunction(this.paperModelSize.getHeight()));

        return retval;
    }

    @Override
    protected void computeMeshes()
    {
        if(this.stemMesh != null)
        {
            this.detachChild(this.stemMesh);
            this.stemMesh = null;
        }

        if(this.leaveLeftMesh != null)
        {
            this.detachChild(this.leaveLeftMesh);
            this.leaveLeftMesh = null;
        }

        if(this.leaveRightMesh != null)
        {
            this.detachChild(this.leaveRightMesh);
            this.leaveRightMesh = null;
        }




        ArrayList<IPoint> stemPoints = new ArrayList<>();

        stemPoints.add(new Point(214, 713));
        stemPoints.add(new Point(231, 716));

        stemPoints.add(new Point(208, 695));
        stemPoints.add(new Point(243, 703));

        stemPoints.add(new Point(189, 546));
        stemPoints.add(new Point(209, 546));

        stemPoints.add(new Point(190, 440));
        stemPoints.add(new Point(209, 440));

        stemPoints.add(new Point(206, 344));
        stemPoints.add(new Point(225, 334));

        stemPoints.add(new Point(246, 210));
        stemPoints.add(new Point(253, 210));



        this.stemMesh = this.getNewMesh(this.stemColor, stemPoints);
        this.attachChild(this.stemMesh);

        ArrayList<IPoint> leavesLeftPoints = new ArrayList<>();

        leavesLeftPoints.add(new Point(190, 438));
        leavesLeftPoints.add(new Point(191, 423));
        leavesLeftPoints.add(new Point(70, 381));
        leavesLeftPoints.add(new Point(71, 331));
        leavesLeftPoints.add(new Point(11, 307));
        leavesLeftPoints.add(new Point(18, 303));

        this.leaveLeftMesh = this.getNewMesh(this.colorLeft, leavesLeftPoints);
        this.attachChild(this.leaveLeftMesh);



        ArrayList<IPoint> leavesRightPoints = new ArrayList<>();

        leavesRightPoints.add(new Point(213, 552));
        leavesRightPoints.add(new Point(208, 528));

        leavesRightPoints.add(new Point(283, 510));
        leavesRightPoints.add(new Point(270, 448));

        leavesRightPoints.add(new Point(378, 433));
        leavesRightPoints.add(new Point(377, 376));

        leavesRightPoints.add(new Point(424, 366));
        leavesRightPoints.add(new Point(420, 362));

        this.leaveRightMesh = this.getNewMesh(this.colorRight, leavesRightPoints);
        this.attachChild(this.leaveRightMesh);
    }
}
