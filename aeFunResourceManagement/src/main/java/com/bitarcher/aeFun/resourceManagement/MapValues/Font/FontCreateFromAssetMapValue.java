package com.bitarcher.aeFun.resourceManagement.MapValues.Font;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateFromAssetResourceInfo;

import org.andengine.opengl.font.FontFactory;

/**
 * Created by michel on 12/01/15.
 */
public class FontCreateFromAssetMapValue extends BaseFontMapValue{
    public FontCreateFromAssetMapValue(IResourceManager resourceManager, IFontCreateFromAssetResourceInfo fontCreateFromAssetResourceInfo) {
        this._tObj = FontFactory.createFromAsset(resourceManager.getEngine().getFontManager(),
                resourceManager.getEngine().getTextureManager(),
                fontCreateFromAssetResourceInfo.getTextureWidth(),
                fontCreateFromAssetResourceInfo.getTextureHeight(),
                resourceManager.getContext().getAssets(),
                fontCreateFromAssetResourceInfo.getFilename(),
                fontCreateFromAssetResourceInfo.getFontSize(),
                fontCreateFromAssetResourceInfo.isAntialiased(),
                fontCreateFromAssetResourceInfo.getForeColor());

        this._tObj.load();
    }
}
