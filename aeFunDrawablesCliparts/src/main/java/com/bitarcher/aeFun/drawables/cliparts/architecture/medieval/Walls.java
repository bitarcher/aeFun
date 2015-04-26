package com.bitarcher.aeFun.drawables.cliparts.architecture.medieval;

import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.mvc.MvcImageTuple;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.BitmapTexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos.OneAssetBitmapTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 20/04/15.
 */
public class Walls {
    Medieval medieval;
    OneAssetBitmapTexture[] oneTexture;
    MvcImageTuple[] mvcImageTuple;

    final int numOfWalls = 3;

    public int getNumOfWalls() {
        return numOfWalls;
    }

    BitmapTexturesSetFromAssetResourceInfo bitmapTexturesSetFromAssetResourceInfo;

    public IImage getWall(int wallNum) {
        return this.mvcImageTuple[wallNum % this.numOfWalls];
    }

    public Walls(Medieval medieval) {
        this.medieval = medieval;

        this.bitmapTexturesSetFromAssetResourceInfo = new BitmapTexturesSetFromAssetResourceInfo("aeFun/drawables/cliparts/architecture/medieval/walls", 512, 256, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT, "architecture/medieval/walls/");

        this.oneTexture = new OneAssetBitmapTexture[this.numOfWalls];
        this.mvcImageTuple = new MvcImageTuple[this.numOfWalls];
        float[] ratios = new float[]{236f / 236f, 94f / 120f, 79f / 134f};

        for(int i = 0 ; i < this.numOfWalls; i++) {
            String iStr = new Integer(i).toString();
            this.oneTexture[i] = new OneAssetBitmapTexture(iStr, "wall" + iStr + ".png");
            this.bitmapTexturesSetFromAssetResourceInfo.addOneTexture(this.oneTexture[i]);
            this.mvcImageTuple[i] = new MvcImageTuple(this.bitmapTexturesSetFromAssetResourceInfo, iStr, ratios[i]);
        }
    }
}
