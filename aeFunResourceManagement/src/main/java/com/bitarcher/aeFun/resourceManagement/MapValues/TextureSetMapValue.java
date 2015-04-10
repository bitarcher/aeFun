/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.resourceManagement.MapValues;

import com.bitarcher.aeFun.resourceManagement.MapValues.SubValues.ITextureSetMapValue;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneTexture;
import com.bitarcher.aeFun.resourceManagement.MapValues.SubValues.OneTextureSV;
import com.bitarcher.aeFun.resourceManagement.ResourceManager;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.debug.Debug;

import java.util.HashMap;

/**
 * Created by michel on 26/01/15.
 */
public abstract class TextureSetMapValue
    <TTexturesSetResourceInfo extends ITexturesSetResourceInfo<TOneTextureResourceInfo>,
            TOneTextureResourceInfo extends IOneTexture,
            TOneTextureSV extends OneTextureSV<TOneTextureResourceInfo>
            >
    extends MapValue
    implements ITextureSetMapValue {

    BuildableBitmapTextureAtlas texture;
    HashMap<String, TOneTextureSV> hashMap;
    TTexturesSetResourceInfo texturesSetResourceInfo;

    public TTexturesSetResourceInfo getTexturesSetResourceInfo()
    {
        return this.texturesSetResourceInfo;
    }

    public BuildableBitmapTextureAtlas getTexture()
    {
        return this.texture;
    }

    @Override
    public void clean() {
        if(this.texture != null)
        {
            if(this.texture.isLoadedToHardware())
            {
                this.texture.unload();
            }
        }
        this.hashMap.clear();
    }

    public TextureSetMapValue(ResourceManager resourceManager, TTexturesSetResourceInfo texturesSetResourceInfo) {
        this.texturesSetResourceInfo = texturesSetResourceInfo;
        this.hashMap = new HashMap<>();

        this.beforeLoadingTextures(resourceManager, texturesSetResourceInfo);

        TextureOptions textureOptions = texturesSetResourceInfo.getTextureOptions();

        if(textureOptions == null)
        {
            textureOptions = TextureOptions.DEFAULT;
        }

        this.texture = new BuildableBitmapTextureAtlas(resourceManager.getEngine().getTextureManager(),
                texturesSetResourceInfo.getAtlasWidth(), texturesSetResourceInfo.getAtlasHeight(),
                texturesSetResourceInfo.getBitmapTextureFormat(), textureOptions);

        for(TOneTextureResourceInfo oneTextureResourceInfo : texturesSetResourceInfo.getTextureList())
        {
            TOneTextureSV oneTextureMapValue = this.createOneTexture(resourceManager, oneTextureResourceInfo);

            this.hashMap.put(oneTextureResourceInfo.getName(), oneTextureMapValue);
        }


        try {
            this.texture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
            this.texture.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }

        this.afterLoadingTextures(resourceManager, texturesSetResourceInfo);
    }

    protected void beforeLoadingTextures(ResourceManager resourceManager, TTexturesSetResourceInfo texturesSetResourceInfo)
    {

    }

    protected void afterLoadingTextures(ResourceManager resourceManager, TTexturesSetResourceInfo texturesSetResourceInfo)
    {

    }

    protected abstract TOneTextureSV createOneTexture(IResourceManager resourceManager, TOneTextureResourceInfo oneTextureResourceInfo);

    public ITextureRegion getTextureRegionByName(String name) {
        return this.hashMap.get(name).getTextureRegion();
    }
}
