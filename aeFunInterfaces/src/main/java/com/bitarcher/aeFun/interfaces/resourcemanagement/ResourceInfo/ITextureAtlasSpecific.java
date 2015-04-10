package com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 16/03/15.
 */
public interface ITextureAtlasSpecific {
    int getAtlasWidth();
    int getAtlasHeight();
    BitmapTextureFormat getBitmapTextureFormat();
    TextureOptions getTextureOptions();
}
