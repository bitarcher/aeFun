package com.bitarcher.aeFun.drawables.cliparts.ArchitectureF.MedievalF;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.cliparts.ArchitectureF.MedievalF.Medieval;
import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.mvc.MvcImageTuple;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.BitmapTexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos.OneAssetBitmapTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 20/04/15.
 */
public class Doors {
    Medieval medieval;
    OneAssetBitmapTexture[] woodDoorOneTexture;
    MvcImageTuple[] woodDoorMvcImageTuple;

    final int numOfDoors = 3;

    public int getNumOfDoors() {
        return numOfDoors;
    }

    BitmapTexturesSetFromAssetResourceInfo bitmapTexturesSetFromAssetResourceInfo;

    public IImage getWoodDoor(int woodDoorNum) {
        return this.woodDoorMvcImageTuple[woodDoorNum % this.numOfDoors];
    }

    public Doors(Medieval medieval) {
        this.medieval = medieval;



        this.bitmapTexturesSetFromAssetResourceInfo = new BitmapTexturesSetFromAssetResourceInfo("aeFun/drawables/cliparts/architecture/medieval/door", 256, 256, BitmapTextureFormat.RGBA_4444, TextureOptions.DEFAULT, "architecture/medieval/door/");

        this.woodDoorOneTexture = new OneAssetBitmapTexture[this.numOfDoors];
        this.woodDoorMvcImageTuple = new MvcImageTuple[this.numOfDoors];
        float[] ratios = new float[]{100f / 190f, 82f/120f, 72f/112f};

        for(int i = 0 ; i < this.numOfDoors ; i++) {
            String iStr = new Integer(i).toString();
            this.woodDoorOneTexture[i] = new OneAssetBitmapTexture("1", "wood_door_" + iStr + ".png");
            this.bitmapTexturesSetFromAssetResourceInfo.addOneTexture(this.woodDoorOneTexture[i]);
            this.woodDoorMvcImageTuple[i] = new MvcImageTuple(this.bitmapTexturesSetFromAssetResourceInfo, iStr, ratios[i]);
        }
    }
}
