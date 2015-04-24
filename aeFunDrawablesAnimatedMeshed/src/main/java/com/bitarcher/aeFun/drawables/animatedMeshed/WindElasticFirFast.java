package com.bitarcher.aeFun.drawables.animatedMeshed;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.geometry.Point;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.IPoint;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/**
 * Created by michel on 24/04/15.
 */
public class WindElasticFirFast extends WindElasticFirBase {


    public WindElasticFirFast(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager, float windStrength, EnumSide windSide, Color rootColor, Color leftColor, Color rightColor, Color topColor, int numOfStages) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager, windStrength, windSide, rootColor, leftColor, rightColor, topColor, numOfStages);
    }

    public WindElasticFirFast(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, vertexBufferObjectManager);
    }


    @Override
    protected ArrayList<IPoint> getRootPoints() {

        ArrayList<IPoint> retval = new ArrayList<>();

        retval.add(new Point(9, 0));
        retval.add(new Point(11, 0));

        retval.add(new Point(9, 3));
        retval.add(new Point(11, 3));

        return retval;
    }
}
