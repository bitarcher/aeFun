package com.bitarcher.aeFun.resourceManagement.MapValues;

import android.util.Log;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IAnimationResourceInfo;


import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.util.GLState;

/**
 * Created by michel on 13/01/15.
 */
public abstract class AnimationMapValue<TAnimationResourceInfo extends IAnimationResourceInfo>  extends MapValue {
    protected IResourceManager resourceManager;
    protected TAnimationResourceInfo animationResourceInfo;
    protected BuildableBitmapTextureAtlas buildableBitmapTextureAtlas;
    protected ITiledTextureRegion tiledTextureRegion;
    protected AnimatedSprite animatedSprite;

    public IResourceManager getResourceManager() {
        return resourceManager;
    }

    public TAnimationResourceInfo getAnimationResourceInfo() {
        return animationResourceInfo;
    }

    public BuildableBitmapTextureAtlas getBuildableBitmapTextureAtlas() {
        return buildableBitmapTextureAtlas;
    }

    public ITiledTextureRegion getTiledTextureRegion() {
        return tiledTextureRegion;
    }

    public AnimatedSprite getAnimatedSprite() {
        return animatedSprite;
    }

    public AnimationMapValue(IResourceManager resourceManager, final TAnimationResourceInfo animationResourceInfo) {
        this.resourceManager = resourceManager;
        this.animationResourceInfo = animationResourceInfo;

        String mPreviousAssetsBase = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(animationResourceInfo.getAssetsBase());


        TextureOptions textureOptions = animationResourceInfo.getTextureOptions();

        if(textureOptions == null)
        {
            textureOptions = TextureOptions.DEFAULT;
        }

        this.buildableBitmapTextureAtlas = new BuildableBitmapTextureAtlas(resourceManager.getEngine().getTextureManager(),
                animationResourceInfo.getAtlasWidth(), animationResourceInfo.getAtlasHeight(), animationResourceInfo.getBitmapTextureFormat(), textureOptions);

        this.tiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.buildableBitmapTextureAtlas, this.resourceManager.getContext(), this.animationResourceInfo.getFilename(), this.animationResourceInfo.getNumOfColumns(), this.animationResourceInfo.getNumOfRows());

        try
        {
            this.buildableBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
        }
        catch (ITextureAtlasBuilder.TextureAtlasBuilderException e)
        {
            Log.v("Bitmap animation load", "Exception:" + e.getMessage());
        }

        this.buildableBitmapTextureAtlas.load();

        this.animatedSprite = new AnimatedSprite(this.animationResourceInfo.getInitialX(), this.animationResourceInfo.getInitialY(), this.tiledTextureRegion, this.resourceManager.getEngine().getVertexBufferObjectManager())
        {

            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                if(animationResourceInfo.isEnableDithering())
                {

                    pGLState.enableDither();
                }
                super.preDraw(pGLState, pCamera);
            }

            @Override
            protected void postDraw(GLState pGLState, Camera pCamera) {
                pGLState.disableDither();
                super.postDraw(pGLState, pCamera);
            }
        };

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetsBase);
    }

    protected abstract ITiledTextureRegion createTiledTexture(IResourceManager resourceManager, BuildableBitmapTextureAtlas buildableBitmapTextureAtlas, TAnimationResourceInfo animationResourceInfo);


    @Override
    public void clean() {
        this.animatedSprite.clearEntityModifiers();
        this.animatedSprite.clearUpdateHandlers();
        this.animatedSprite = null;
        if(this.buildableBitmapTextureAtlas != null)
        {
            if(this.buildableBitmapTextureAtlas.isLoadedToHardware())
            {
                this.buildableBitmapTextureAtlas.unload();
            }
            this.buildableBitmapTextureAtlas = null;
        }
    }
}
