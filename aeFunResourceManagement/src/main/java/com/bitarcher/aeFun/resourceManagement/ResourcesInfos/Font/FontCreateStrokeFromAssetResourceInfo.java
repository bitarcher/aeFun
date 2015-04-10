package com.bitarcher.aeFun.resourceManagement.ResourcesInfos.Font;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontCreateStrokeFromAssetResourceInfo;

/**
 * Created by michel on 12/01/15.
 */
public class FontCreateStrokeFromAssetResourceInfo extends FontCreateFromAssetResourceInfo implements IFontCreateStrokeFromAssetResourceInfo {

    int backColor;
    float strokeWidth;

    public FontCreateStrokeFromAssetResourceInfo(String name, int textureWidth, int textureHeight, float fontSize, int foreColor, boolean antialiased, String filename, int backColor, float strokeWith) {
        super(name, textureWidth, textureHeight, fontSize, foreColor, antialiased, filename);
        this.backColor = backColor;
        this.strokeWidth = strokeWith;
    }

    @Override
    public int getBackColor() {
        return this.backColor;
    }

    @Override
    public float getStrokeWidth() {
        return strokeWidth;
    }
}
