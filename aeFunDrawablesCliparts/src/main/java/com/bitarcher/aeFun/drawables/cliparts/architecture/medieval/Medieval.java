package com.bitarcher.aeFun.drawables.cliparts.architecture.medieval;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.drawables.cliparts.architecture.Architecture;
import com.bitarcher.aeFun.drawables.cliparts.architecture.medieval.houses.Houses;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SingleBitmapTextureSetFromAssetResourceInfo;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 20/04/15.
 */
public class Medieval {

    Architecture architecture;
    Doors doors;
    Houses houses;
    Walls walls;
    SingleBitmapTextureSetFromAssetResourceInfo windMillFan;

    public Doors getDoors() {
        if(this.doors == null)
        {
            this.doors = new Doors(this);
        }

        return this.doors;
    }

    public Walls getWalls()
    {
        if(this.walls == null)
        {
            this.walls = new Walls(this);
        }

        return this.walls;
    }

    public Houses getHouses() {
        if(this.houses == null)
        {
            this.houses = new Houses(this);
        }

        return this.houses;
    }

    public SingleBitmapTextureSetFromAssetResourceInfo getWindMillFan()
    {
        if(this.windMillFan == null)
        {
            this.windMillFan = new SingleBitmapTextureSetFromAssetResourceInfo("windmill0.png", 128, 128, BitmapTextureFormat.RGBA_4444,
                    TextureOptions.DEFAULT, "architecture/medieval/", 1);
        }

        return this.windMillFan;
    }

    public Architecture getArchitecture() {
        return architecture;
    }



    public Medieval(Architecture architecture) {
        this.architecture = architecture;
    }
}
