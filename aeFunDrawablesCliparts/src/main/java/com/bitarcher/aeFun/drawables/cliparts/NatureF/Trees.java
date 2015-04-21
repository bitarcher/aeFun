package com.bitarcher.aeFun.drawables.cliparts.NatureF;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.mvc.MvcImageTuple;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.BitmapTexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos.OneAssetBitmapTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 21/04/15.
 */
public class Trees {
    Nature nature;

    OneAssetBitmapTexture[] treeOneTexture;
    MvcImageTuple[] treeMvcImageTuple;

    final int numOfDoors = 3;

    public int getNumOfDoors() {
        return numOfDoors;
    }

    BitmapTexturesSetFromAssetResourceInfo bitmapTexturesSetFromAssetResourceInfo;

    public IImage getTree(int treeNum) {
        return this.treeMvcImageTuple[treeNum % this.numOfDoors];
    }


    public Nature getNature() {
        return nature;
    }

    public Trees(Nature nature) {
        this.nature = nature;


        this.bitmapTexturesSetFromAssetResourceInfo = new BitmapTexturesSetFromAssetResourceInfo("aeFun/drawables/cliparts/nature/tree", 128, 512, BitmapTextureFormat.RGBA_4444, TextureOptions.DEFAULT, "nature/tree");

        this.treeOneTexture = new OneAssetBitmapTexture[this.numOfDoors];
        this.treeMvcImageTuple = new MvcImageTuple[this.numOfDoors];
        float[] ratios = new float[]{128f / 128f, 128f/148f, 128f/236f};

        for(int i = 0 ; i < this.numOfDoors ; i++) {
            String iStr = new Integer(i).toString();
            this.treeOneTexture[i] = new OneAssetBitmapTexture("1", "tree_" + iStr + ".png");
            this.bitmapTexturesSetFromAssetResourceInfo.addOneTexture(this.treeOneTexture[i]);
            this.treeMvcImageTuple[i] = new MvcImageTuple(this.bitmapTexturesSetFromAssetResourceInfo, iStr, ratios[i]);
        }
    }
}
