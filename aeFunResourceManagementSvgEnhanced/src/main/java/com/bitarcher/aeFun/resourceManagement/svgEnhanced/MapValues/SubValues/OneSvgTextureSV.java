package com.bitarcher.aeFun.resourceManagement.svgEnhanced.MapValues.SubValues;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneSvgTexture;
import com.bitarcher.aeFun.resourceManagement.MapValues.SubValues.OneTextureSV;
import com.bitarcher.aeFun.resourceManagement.MapValues.TextureSetMapValue;

/*
 * Created by michel on 13/01/15.
 */

public abstract class OneSvgTextureSV<TOneSvgTextureResourceInfo extends IOneSvgTexture> extends OneTextureSV<TOneSvgTextureResourceInfo> {

    public OneSvgTextureSV(IResourceManager resourceManager, TextureSetMapValue textureSetMapValue, TOneSvgTextureResourceInfo oneTextureResourceInfo) {
        super(resourceManager, textureSetMapValue, oneTextureResourceInfo);
    }
}
