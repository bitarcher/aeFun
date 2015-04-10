/*
 * Copyright (c) 2015. 
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.resourceManagement.ResourcesInfos;

import android.content.Context;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBitmapTexturesSetFromResIdsResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneResBitmapTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 26/01/15.
 */
public class BitmapTexturesSetFromResIdsResourceInfo extends TexturesSetFromResIdsResourceInfo<IOneResBitmapTexture> implements IBitmapTexturesSetFromResIdsResourceInfo {
    public BitmapTexturesSetFromResIdsResourceInfo(String name, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, Context context) {
        super(name, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions, context);
    }
}
