package com.bitarcher.aeFun.drawables.animatedMeshed.nature.trees;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import android.util.Log;

import com.bitarcher.aeFun.colors.ColorSpaces;
import com.bitarcher.aeFun.colors.HSB;
import com.bitarcher.aeFun.colors.RandomColors;
import com.bitarcher.aeFun.drawables.animatedMeshed.tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.interfaces.basicioc.ITFactory;
import com.bitarcher.aeFun.interfaces.colors.IHSB;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.geometry.IPositionAndSizeOwner;

import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.Random;

/**
 * Created by michel on 29/04/15.
 */
public class WindTreeFactory implements ITFactory<WindElasticCompositeMeshes, IPositionAndSizeOwner> {
    Random random = new Random();
    RandomColors randomColors;
    ColorSpaces colorSpaces = new ColorSpaces();
    boolean drawFast = false;
    VertexBufferObjectManager vertexBufferObjectManager;
    boolean autumnColorScheme;

    public WindTreeFactory(VertexBufferObjectManager vertexBufferObjectManager, boolean drawFast, boolean autumnColorScheme) {
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.randomColors = new RandomColors(this.random);
        this.drawFast = drawFast;
        this.autumnColorScheme = autumnColorScheme;
    }

    public WindTreeFactory(VertexBufferObjectManager vertexBufferObjectManager)
    {
        this(vertexBufferObjectManager, false, true);
    }

    @Override
    public WindElasticCompositeMeshes make(IPositionAndSizeOwner positionAndSizeOwner) {
        WindElasticCompositeMeshes retval = null;

        int type = random.nextInt(2);


        switch (type)
        {
            case 0:
                retval = this.makeBeechwood(positionAndSizeOwner);
                break;
            case 1:
                retval = this.makeFir(positionAndSizeOwner);
                break;
        }

        if(retval == null)
        {
            Log.e("aeFun:animatedMeshesd", "wind tree factory returns null width type " + new Integer(type).toString());
        }

        return retval;
    }

    public WindElasticFirBase makeFir(IPositionAndSizeOwner positionAndSizeOwner)
    {
        WindElasticFirBase retval = null;

        Color rootColor, leftColor, rightColor, topColor;

        rootColor = this.randomColors.getRandomColor(
                new Color(147f/ 255f, 103f/ 255f, 52f/ 255f),
                new Color(221f/ 255f, 193f/ 255f, 108f/ 255f),
                new Color(216f/ 255f, 123f/ 255f, 33f/ 255f));


        leftColor = this.randomColors.getRandomColor(
                new Color(155f/ 255f, 220f/ 255f, 120f/ 255f),
                new Color(42f/ 255f, 185f/ 255f, 38f/ 255f),
                new Color(87f/ 255f, 140f/ 255f, 66f/ 255f));


        rightColor = this.randomColors.getRandomColor(
                new Color(93f/ 255f, 134f/ 255f, 91f/ 255f),
                new Color(45f/ 255f, 89f/ 255f, 43f/ 255f),
                new Color(54f/ 255f, 130f/ 255f, 70f/ 255f));

        topColor = this.randomColors.getRandomColor(
                new Color(64f/ 255f, 223f/ 255f, 64f/ 255f),
                new Color(64f/ 255f, 213f/ 255f, 6f/ 255f),
                new Color(189f/ 255f, 245f/ 255f, 19f/ 255f));




        if(this.drawFast)
        {
            retval = new WindElasticFirFast(
                    positionAndSizeOwner.getPosition().getX(),
                    positionAndSizeOwner.getPosition().getY(),
                    positionAndSizeOwner.getSize().getWidth(),
                    positionAndSizeOwner.getSize().getHeight(),
                    this.vertexBufferObjectManager,
                    0f, EnumSide.Right, rootColor, leftColor, rightColor, topColor, 5);
        }
        else {
            retval = new WindElasticFirPretty(positionAndSizeOwner.getPosition().getX(),
                    positionAndSizeOwner.getPosition().getY(),
                    positionAndSizeOwner.getSize().getWidth(),
                    positionAndSizeOwner.getSize().getHeight(),
                    this.vertexBufferObjectManager,
                    0f, EnumSide.Right, rootColor, leftColor, rightColor, topColor, 7);
        }

        return retval;
    }

    public WindElasticTree1 makeBeechwood(IPositionAndSizeOwner positionAndSizeOwner)
    {
        WindElasticTree1 retval = null;

        Color rootColor, leavesAColor, leavesABorderColor, leavesBColor;

        rootColor = this.randomColors.getRandomColor(
                new Color(147f/ 255f, 103f/ 255f, 52f/ 255f),
                new Color(221f/ 255f, 193f/ 255f, 108f/ 255f),
                new Color(216f/ 255f, 123f/ 255f, 33f/ 255f));

        leavesAColor = this.randomColors.getRandomColor(
                new Color(155f/ 255f, 220f/ 255f, 120f/ 255f),
                new Color(42f/ 255f, 185f/ 255f, 38f/ 255f),
                new Color(87f/ 255f, 140f/ 255f, 66f/ 255f));

        if(this.autumnColorScheme)
        {
            leavesAColor = this.randomColors.getRandomColor(leavesAColor, Color.RED);
        }


        IHSB leavesAColorHsb = this.colorSpaces.color_to_HSB(leavesAColor);

        HSB leavesABorderColorHsb = new HSB(leavesAColorHsb);
        leavesABorderColorHsb.setBrightness(leavesAColorHsb.getBrightness() * 0.8f);
        leavesABorderColorHsb.setSaturation(leavesAColorHsb.getSaturation() * 0.8f);
        leavesABorderColor = this.colorSpaces.HSB_to_Color(leavesABorderColorHsb);

        HSB leavesBColorHsb = new HSB(leavesAColorHsb);
        leavesBColorHsb.setBrightness(leavesAColorHsb.getBrightness() * 1.1f);
        leavesBColor = this.colorSpaces.HSB_to_Color(leavesBColorHsb);

        retval = new WindElasticTree1(positionAndSizeOwner.getPosition().getX(),
                positionAndSizeOwner.getPosition().getY(),
                positionAndSizeOwner.getSize().getWidth(),
                positionAndSizeOwner.getSize().getHeight(),
                this.vertexBufferObjectManager,
                0f, EnumSide.Right,
                rootColor,
                leavesAColor,
                leavesABorderColor,
                leavesBColor
                );

        return retval;
    }
}
