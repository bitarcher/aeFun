/*
 * Copyright (c) 2015. 
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.resourceManagement.ResourcesInfos;

import android.content.Context;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetFromResIdsResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneResTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 26/01/15.
 */
public class TexturesSetFromResIdsResourceInfo<TOneResTexture extends IOneResTexture>  extends TexturesSetResourceInfo<TOneResTexture> implements ITexturesSetFromResIdsResourceInfo<TOneResTexture> {

    Context context;

    public TexturesSetFromResIdsResourceInfo(String name, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, Context context) {
        super(name, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions);
        this.context = context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }
}
