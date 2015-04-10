package com.bitarcher.aeFun.resourceManagement.ResourcesInfos.Font;

import android.graphics.Typeface;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateFromTypeFaceResourceInfo;

/**
 * Created by michel on 12/01/15.
 */
public class FontCreateFromTypeFaceResourceInfo extends BaseFontResourceInfo implements IFontCreateFromTypeFaceResourceInfo {

    Typeface typeFace;

    public FontCreateFromTypeFaceResourceInfo(String name, int textureWidth, int textureHeight, float fontSize, int foreColor, boolean antialiased, Typeface typeFace) {
        super(name, textureWidth, textureHeight, fontSize, foreColor, antialiased);
        this.typeFace = typeFace;
    }

    @Override
    public Typeface getTypeFace() {
        return this.typeFace;
    }
}


