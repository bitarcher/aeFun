package com.bitarcher.aeFun.resourceManagement.ResourcesInfos.Font;

import android.graphics.Typeface;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateStrokeFromTypeFaceResourceInfo;

/**
 * Created by michel on 12/01/15.
 */
public class FontCreateStrokeFromTypeFaceResourceInfo extends FontCreateFromTypeFaceResourceInfo implements IFontCreateStrokeFromTypeFaceResourceInfo {

    int backColor;
    float strokeWidth;

    public FontCreateStrokeFromTypeFaceResourceInfo(String name, int textureWidth, int textureHeight, float fontSize, int foreColor, boolean antialiased, Typeface typeFace, int backColor, float strokeWidth) {
        super(name, textureWidth, textureHeight, fontSize, foreColor, antialiased, typeFace);
        this.backColor = backColor;
        this.strokeWidth = strokeWidth;
    }

    @Override
    public float getStrokeWidth() {
        return strokeWidth;
    }

    @Override
    public int getBackColor() {
        return this.backColor;
    }
}
