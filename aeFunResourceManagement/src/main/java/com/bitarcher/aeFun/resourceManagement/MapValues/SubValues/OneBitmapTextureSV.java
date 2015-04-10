package com.bitarcher.aeFun.resourceManagement.MapValues.SubValues;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneBitmapTexture;

/**
 * Created by michel on 13/01/15.
 */
public abstract class OneBitmapTextureSV<TOneBitmapTextureResourceInfo extends IOneBitmapTexture> extends OneTextureSV<TOneBitmapTextureResourceInfo>
{
    public OneBitmapTextureSV(IResourceManager resourceManager, ITextureSetMapValue textureSetMapValue, TOneBitmapTextureResourceInfo oneTextureResourceInfo) {
        super(resourceManager, textureSetMapValue, oneTextureResourceInfo);
    }


}
