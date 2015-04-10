/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneTexture;

import java.util.List;

/**
 * Created by michel on 26/01/15.
 */
public interface ITexturesSetResourceInfo <TOneTexture extends IOneTexture>  extends IResourceInfo, ITextureAtlasSpecific {
    List<TOneTexture> getTextureList();
}
