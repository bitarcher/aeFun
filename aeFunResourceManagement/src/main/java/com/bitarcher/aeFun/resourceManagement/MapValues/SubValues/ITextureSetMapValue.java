package com.bitarcher.aeFun.resourceManagement.MapValues.SubValues;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneTexture;

import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;

/**
 * Created by michel on 13/01/15.
 */
public interface ITextureSetMapValue<TTexturesSetResourceInfo extends ITexturesSetResourceInfo<TOneTextureResourceInfo>,
        TOneTextureResourceInfo extends IOneTexture,
        TOneTextureSV extends OneTextureSV<TOneTextureResourceInfo>
        > {
    TTexturesSetResourceInfo getTexturesSetResourceInfo();
    BuildableBitmapTextureAtlas getTexture();
}
