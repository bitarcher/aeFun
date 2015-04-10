package com.bitarcher.aeFun.examples;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.mvc.MvcImageTuple;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.BitmapTexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos.OneAssetBitmapTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 24/03/15.
 */
public class ResourceInfosSingleton {

    BitmapTexturesSetFromAssetResourceInfo textureAtlas1;
    OneAssetBitmapTexture bitarcherLogo;

    MvcImageTuple bitarcherLogoMvcImageTuple;

    public ITexturesSetResourceInfo getTextureAtlas1()
    {
        return this.textureAtlas1;
    }

    public IImage getBitarcherLogoMvcImage()
    {
        return this.bitarcherLogoMvcImageTuple;
    }

    private static ResourceInfosSingleton ourInstance = new ResourceInfosSingleton();

    public static ResourceInfosSingleton getInstance() {
        return ourInstance;
    }

    private ResourceInfosSingleton() {
        this.textureAtlas1 = new BitmapTexturesSetFromAssetResourceInfo("textureAtlas1", 1024, 1024, BitmapTextureFormat.RGBA_8888, TextureOptions.DEFAULT, "");;
        this.bitarcherLogo = new OneAssetBitmapTexture("bitArcherLogo", "logo_bitarcher.png");
        this.textureAtlas1.addOneTexture(this.bitarcherLogo);

        this.bitarcherLogoMvcImageTuple = new MvcImageTuple(this.textureAtlas1, this.bitarcherLogo.getName(), 156f/162f);
    }


}

