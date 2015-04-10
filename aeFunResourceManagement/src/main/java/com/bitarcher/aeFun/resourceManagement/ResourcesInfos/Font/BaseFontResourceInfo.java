package com.bitarcher.aeFun.resourceManagement.ResourcesInfos.Font;

import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.ResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.Font.IFontResourceInfo;

/**
 * Created by michel on 12/01/15.
 */
public abstract class BaseFontResourceInfo extends ResourceInfo implements IFontResourceInfo{
    int textureWidth;
    int textureHeight;
    float fontSize;
    int foreColor;
    boolean antialiased;

    protected BaseFontResourceInfo(String name, int textureWidth, int textureHeight, float fontSize, int foreColor, boolean antialiased) {
        super(name);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.fontSize = fontSize;
        this.foreColor = foreColor;
        this.antialiased = antialiased;
    }

    @Override
    public int getTextureWidth() {
        return textureWidth;
    }

    @Override
    public int getTextureHeight() {
        return textureHeight;
    }

    @Override
    public float getFontSize() {
        return fontSize;
    }

    @Override
    public int getForeColor() {
        return foreColor;
    }

    @Override
    public boolean isAntialiased() {
        return antialiased;
    }
}
