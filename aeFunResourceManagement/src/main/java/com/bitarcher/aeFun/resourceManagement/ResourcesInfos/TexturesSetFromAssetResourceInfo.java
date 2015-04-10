package com.bitarcher.aeFun.resourceManagement.ResourcesInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneAssetTexture;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

/**
 * Created by michel on 12/01/15.
 */
public class TexturesSetFromAssetResourceInfo<TOneAssetTexture extends IOneAssetTexture>  extends TexturesSetResourceInfo<TOneAssetTexture> implements ITexturesSetFromAssetResourceInfo<TOneAssetTexture> {

    protected String assetsBase;

    @Override
    public String getAssetsBase() {
        return this.assetsBase;
    }


    public TexturesSetFromAssetResourceInfo(String name, int atlasWidth, int atlasHeight, BitmapTextureFormat bitmapTextureFormat, TextureOptions textureOptions, String assetsBase) {
        super(name, atlasWidth, atlasHeight, bitmapTextureFormat, textureOptions);
        this.assetsBase = assetsBase;
    }
}
