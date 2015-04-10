package com.bitarcher.aeFun.resourceManagement.ResourcesInfos;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import android.content.Context;

import com.bitarcher.aeFun.interfaces.mvc.IImage;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos.OneResBitmapTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 18/03/15.
 */
public class SingleBitmapTextureSetFromResIdsResourceInfo extends BitmapTexturesSetFromResIdsResourceInfo implements IImage{

    float aspectRatio;

    @Override
    public float getAspectRatio() {
        return aspectRatio;
    }

    public SingleBitmapTextureSetFromResIdsResourceInfo(String name, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, Context context, int resId, float aspectRatio) {
        super(name, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions, context);

        this.addOneTexture(new OneResBitmapTexture(name, resId));
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
