package com.bitarcher.aeFun.resourceManagement.ResourcesInfos;

import android.content.Context;

import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos.OneAssetBitmapTexture;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos.OneResBitmapTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 18/03/15.
 */
public class SingleBitmapTextureSetFromAssetResourceInfo extends BitmapTexturesSetFromAssetResourceInfo implements IImage{

    float aspectRatio;

    @Override
    public float getAspectRatio() {
        return aspectRatio;
    }

    public SingleBitmapTextureSetFromAssetResourceInfo(String filename, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, String assetsBase, float aspectRatio) {
        super(assetsBase + "/" + filename, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions, assetsBase);

        this.addOneTexture(new OneAssetBitmapTexture(name, filename));
        this.aspectRatio = aspectRatio;
    }

    @Override
    public ITexturesSetResourceInfo getTextureSetResourceInfo() {
        return this;
    }

    @Override
    public String getTextureName() {
        return this.name;
    }
}
