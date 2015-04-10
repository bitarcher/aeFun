/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.resourceManagement.MapValues.SubValues;

import android.content.Context;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneResBitmapTexture;
import com.bitarcher.aeFun.resourceManagement.MapValues.TextureSetFromResMapValue;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by michel on 26/01/15.
 */
public class OneResBitmapTextureSV extends OneBitmapTextureSV<IOneResBitmapTexture> {
    public OneResBitmapTextureSV(IResourceManager resourceManager, ITextureSetMapValue textureSetMapValue, IOneResBitmapTexture oneTextureResourceInfo) {
        super(resourceManager, textureSetMapValue, oneTextureResourceInfo);
    }

    @Override
    protected ITextureRegion createTextureRegionFromResourceInfo(IResourceManager resourceManager, ITextureSetMapValue textureSetMapValue, IOneResBitmapTexture oneTextureResourceInfo) {
        ITextureRegion retval = null;

        TextureSetFromResMapValue textureSetFromResMapValue = (TextureSetFromResMapValue)textureSetMapValue;

        Context context = textureSetFromResMapValue.getContext();

        retval = BitmapTextureAtlasTextureRegionFactory.createFromResource(textureSetMapValue.getTexture(),
                    context, oneTextureResourceInfo.getResId());

        return retval;
    }

}
