package com.bitarcher.aeFun.resourceManagement.ResourcesInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBitmapAnimationResourceInfo;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 10/01/15.
 */
public class BitmapAnimationResourceInfo extends AnimationResourceInfo implements IBitmapAnimationResourceInfo {
    public BitmapAnimationResourceInfo(String name, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, String assetsBase, String filename, int numOfColumns, int numOfRows, float initialX, float initialY, boolean enableDithering) {
        super(name, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions, assetsBase, filename, numOfColumns, numOfRows, initialX, initialY, enableDithering);
    }
}
