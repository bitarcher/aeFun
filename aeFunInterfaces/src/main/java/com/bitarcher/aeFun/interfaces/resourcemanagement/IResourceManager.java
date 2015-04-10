package com.bitarcher.aeFun.interfaces.resourcemanagement;

import android.content.Context;

import com.bitarcher.aeFun.interfaces.gui.theme.IThemeManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IAnimationResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBuildableBitmapTextureAtlasResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IMusicResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ISoundResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;

import org.andengine.audio.music.Music;
import org.andengine.audio.sound.Sound;
import org.andengine.engine.Engine;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by michel on 07/01/15.
 */
public interface IResourceManager {
    Engine getEngine();
    Context getContext();
    IThemeManager getThemeManager();
    float getCameraWidth();
    float getCameraHeight();

    void setup(final Engine pEngine, final IContextProvider pContextProvider, IThemeManager themeManager);

    void pushRequirement(IResourceInfo resourceInfo) throws EResourceCreationError;
    void pushRequirement(IResourceInfoListGotter resourceInfoListGotter) throws EResourceCreationError;

    void popRequirement(IResourceInfo resourceInfo) throws EResourceNotFound;

    // popped in reversed order so you can managed dependencies between resources
    void popRequirement(IResourceInfoListGotter resourceInfoListGotter) throws EResourceNotFound;

    BuildableBitmapTextureAtlas getBuildableBitmapTextureAtlas(IBuildableBitmapTextureAtlasResourceInfo buildableBitmapTextureAtlasInfo) throws EResourceNotFound;
    ITextureRegion getTextureRegionFromTextureSetByName(ITexturesSetResourceInfo textureSetResourceInfo, String textureName) throws EResourceNotFound;
    AnimatedSprite getAnimatedSpriteFromAnimationResourceInfo(IAnimationResourceInfo animationResourceInfo) throws EResourceNotFound;
    Font getFont(IFontResourceInfo fontResourceInfo) throws EResourceNotFound;
    Sound getSound(ISoundResourceInfo soundResourceInfo) throws EResourceNotFound;
    Music getMusic(IMusicResourceInfo musicResourceInfo) throws EResourceNotFound;
}

