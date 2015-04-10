/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


package com.bitarcher.aeFun.resourceManagement.svgEnhanced.ResourcesInfos.SubInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneResSvgTexture;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos.OneResTexture;

import org.andengine.extension.svg.adt.ISVGColorMapper;


/**
 * Created by michel on 26/01/15.
 */

public class OneResSvgTexture extends OneResTexture implements IOneResSvgTexture {

    int width;
    int height;
    ISVGColorMapper svgColorMapper;

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public ISVGColorMapper getSvgColorMapper() {
        return this.svgColorMapper;
    }


    public OneResSvgTexture(String name, int rawResId, int width, int height, ISVGColorMapper svgColorMapper) {
        super(name, rawResId);

        this.width = width;
        this.height = height;
        this.svgColorMapper = svgColorMapper;
    }

    public OneResSvgTexture(String name, int rawResId, int width, int height) {
        this(name, rawResId, width, height, null);
    }

}
