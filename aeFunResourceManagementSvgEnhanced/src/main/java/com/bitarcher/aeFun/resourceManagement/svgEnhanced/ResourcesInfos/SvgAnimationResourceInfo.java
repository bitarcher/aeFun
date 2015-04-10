
package com.bitarcher.aeFun.resourceManagement.svgEnhanced.ResourcesInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ISvgAnimationResourceInfo;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.AnimationResourceInfo;

import org.andengine.extension.svg.adt.ISVGColorMapper;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.jetbrains.annotations.Nullable;


/**
 * Created by michel on 13/01/15.
 */

public class SvgAnimationResourceInfo extends AnimationResourceInfo implements ISvgAnimationResourceInfo {
    int width;
    int height;
    ISVGColorMapper svgColorMapper;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Nullable
    @Override
    public ISVGColorMapper getSvgColorMapper() {
        return svgColorMapper;
    }

    public SvgAnimationResourceInfo(String name, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, String assetsBase, String filename, int numOfColumns, int numOfRows, float initialX, float initialY, boolean enableDithering, int width, int height, ISVGColorMapper svgColorMapper) {
        super(name, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions, assetsBase, filename, numOfColumns, numOfRows, initialX, initialY, enableDithering);
        this.width = width;
        this.height = height;
        this.svgColorMapper = svgColorMapper;
    }
}

