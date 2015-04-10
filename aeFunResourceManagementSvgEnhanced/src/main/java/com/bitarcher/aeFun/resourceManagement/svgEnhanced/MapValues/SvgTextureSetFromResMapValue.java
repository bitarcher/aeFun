 /* Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
*/


package com.bitarcher.aeFun.resourceManagement.svgEnhanced.MapValues;

import com.bitarcher.aeFun.resourceManagement.svgEnhanced.MapValues.SubValues.OneResSvgTextureSV;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ISvgTexturesSetFromResIdsResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneResSvgTexture;
import com.bitarcher.aeFun.resourceManagement.MapValues.TextureSetFromResMapValue;
import com.bitarcher.aeFun.resourceManagement.ResourceManager;

/*
 * Created by michel on 26/01/15.
*/

public class SvgTextureSetFromResMapValue extends TextureSetFromResMapValue<ISvgTexturesSetFromResIdsResourceInfo,
        IOneResSvgTexture,
        OneResSvgTextureSV
        > {

    @Override
    protected OneResSvgTextureSV createOneTexture(IResourceManager resourceManager,  IOneResSvgTexture iOneResSvgTexture) {
        return new OneResSvgTextureSV(resourceManager, this, iOneResSvgTexture);
    }

    public SvgTextureSetFromResMapValue(ResourceManager resourceManager, ISvgTexturesSetFromResIdsResourceInfo texturesSetResourceInfo) {
        super(resourceManager, texturesSetResourceInfo);
    }
}
