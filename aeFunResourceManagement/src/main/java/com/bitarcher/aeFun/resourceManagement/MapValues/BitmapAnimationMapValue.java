package com.bitarcher.aeFun.resourceManagement.MapValues;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IAnimationResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBitmapAnimationResourceInfo;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

/**
 * Created by michel on 10/01/15.
 */
public class BitmapAnimationMapValue extends  AnimationMapValue  {
    @Override
    protected ITiledTextureRegion createTiledTexture(IResourceManager resourceManager, BuildableBitmapTextureAtlas buildableBitmapTextureAtlas, IAnimationResourceInfo animationResourceInfo) {
        return BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.buildableBitmapTextureAtlas, resourceManager.getContext(), animationResourceInfo.getFilename(), animationResourceInfo.getNumOfColumns(), animationResourceInfo.getNumOfRows());
    }

    public BitmapAnimationMapValue(IResourceManager resourceManager, IBitmapAnimationResourceInfo bitmapAnimationResourceInfo) {
        super(resourceManager, bitmapAnimationResourceInfo);
    }
}
