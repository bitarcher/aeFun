/*
 * Copyright (c) 2015. 
 * Michel Strasser
 * bitarcher.com
 */


package com.bitarcher.aeFun.resourceManagement.svgEnhanced.ResourcesInfos;

import android.content.Context;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ISvgTexturesSetFromResIdsResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneResSvgTexture;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.TexturesSetFromResIdsResourceInfo;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;


/**
 * Created by michel on 26/01/15.
 */

public class SvgTexturesSetFromResIdsResourceInfo extends TexturesSetFromResIdsResourceInfo<IOneResSvgTexture> implements ISvgTexturesSetFromResIdsResourceInfo {
    public SvgTexturesSetFromResIdsResourceInfo(String name, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, Context context) {
        super(name, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions, context);
    }
}

