package com.bitarcher.aeFun.resourceManagement.MapValues.Font;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateStrokeFromAssetResourceInfo;

import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

/**
 * Created by michel on 12/01/15.
 */
public class FontCreateStrokeFromAssetMapValue extends BaseFontMapValue {

    BitmapTextureAtlas fontTexture;

    public FontCreateStrokeFromAssetMapValue(IResourceManager resourceManager, IFontCreateStrokeFromAssetResourceInfo fontCreateStrokeFromAssetResourceInfo) {

        this.fontTexture = new BitmapTextureAtlas(resourceManager.getEngine().getTextureManager(),
                fontCreateStrokeFromAssetResourceInfo.getTextureWidth(),
                fontCreateStrokeFromAssetResourceInfo.getTextureHeight(),
                TextureOptions.BILINEAR);


        this._tObj = FontFactory.createStrokeFromAsset(
                resourceManager.getEngine().getFontManager(),
                this.fontTexture,
                resourceManager.getContext().getAssets(),
                fontCreateStrokeFromAssetResourceInfo.getFilename(),
                fontCreateStrokeFromAssetResourceInfo.getFontSize(),
                fontCreateStrokeFromAssetResourceInfo.isAntialiased(),
                fontCreateStrokeFromAssetResourceInfo.getForeColor(),
                fontCreateStrokeFromAssetResourceInfo.getStrokeWidth(),
                fontCreateStrokeFromAssetResourceInfo.getBackColor());


        this._tObj.load();
        this.fontTexture.load();
    }

    @Override
    public void clean() {
        super.clean();

        if(this.fontTexture != null) {
            this.fontTexture.unload();
            this.fontTexture = null;
        }
    }
}
