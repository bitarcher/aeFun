
package com.bitarcher.aeFun.resourceManagement.svgEnhanced.ResourcesInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ISvgTexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneAssetSvgTexture;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.TexturesSetFromAssetResourceInfo;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;


/**
 * Created by michel on 13/01/15.
 */

public class SvgTexturesSetFromAssetResourceInfo extends TexturesSetFromAssetResourceInfo<IOneAssetSvgTexture> implements ISvgTexturesSetFromAssetResourceInfo {
    public SvgTexturesSetFromAssetResourceInfo(String name, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, String assetsBase) {
        super(name, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions, assetsBase);
    }
}

