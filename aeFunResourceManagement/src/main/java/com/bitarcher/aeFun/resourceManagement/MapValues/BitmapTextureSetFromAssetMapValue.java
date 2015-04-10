package com.bitarcher.aeFun.resourceManagement.MapValues;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBitmapTexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneAssetBitmapTexture;
import com.bitarcher.aeFun.resourceManagement.MapValues.SubValues.OneAssetBitmapTextureSV;
import com.bitarcher.aeFun.resourceManagement.ResourceManager;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;

/**
 * Created by michel on 13/01/15.
 */
public class BitmapTextureSetFromAssetMapValue extends TextureSetFromAssetMapValue<IBitmapTexturesSetFromAssetResourceInfo,
        IOneAssetBitmapTexture,
        OneAssetBitmapTextureSV
        > {

    public BitmapTextureSetFromAssetMapValue(ResourceManager resourceManager, IBitmapTexturesSetFromAssetResourceInfo texturesSetResourceInfo) {
        super(resourceManager, texturesSetResourceInfo);
    }

    @Override
    protected void setAssetBase(String assetBase) {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(assetBase);
    }

    @Override
    protected String getAssetBase() {
        return BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
    }

    @Override
    protected OneAssetBitmapTextureSV createOneTexture(IResourceManager resourceManager, IOneAssetBitmapTexture iOneBitmapTexture) {
        return new OneAssetBitmapTextureSV(resourceManager, this, iOneBitmapTexture);
    }
}
