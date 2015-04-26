package com.bitarcher.aeFun.drawables.cliparts.architecture.medieval.houses;

import com.bitarcher.aeFun.drawables.cliparts.architecture.medieval.Medieval;
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
public class Timbered {
    Houses houses;
    OneAssetBitmapTexture[] timberedOneTexture;
    MvcImageTuple[] timberedMvcImageTuple;

    final int numOfTimbered = 3;

    public int getNumOfTimbered() {
        return numOfTimbered;
    }

    BitmapTexturesSetFromAssetResourceInfo bitmapTexturesSetFromAssetResourceInfo;

    public IImage getTimbered(int timberedNum) {
        return this.timberedMvcImageTuple[timberedNum % this.numOfTimbered];
    }

    public Timbered(Houses houses) {
        this.houses = houses;

        this.bitmapTexturesSetFromAssetResourceInfo = new BitmapTexturesSetFromAssetResourceInfo("aeFun/drawables/cliparts/architecture/medieval/houses", 256, 256, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT, "architecture/medieval/houses/");

        this.timberedOneTexture = new OneAssetBitmapTexture[this.numOfTimbered];
        this.timberedMvcImageTuple = new MvcImageTuple[this.numOfTimbered];
        float[] ratios = new float[]{0.747f, 0.994f, 1.739f};

        for(int i = 0 ; i < this.numOfTimbered; i++) {
            String iStr = new Integer(i).toString();
            this.timberedOneTexture[i] = new OneAssetBitmapTexture(iStr, "timbered" + iStr + ".png");
            this.bitmapTexturesSetFromAssetResourceInfo.addOneTexture(this.timberedOneTexture[i]);
            this.timberedMvcImageTuple[i] = new MvcImageTuple(this.bitmapTexturesSetFromAssetResourceInfo, iStr, ratios[i]);
        }
    }
}
