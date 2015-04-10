/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo;

import android.content.Context;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneResTexture;

/**
 * Created by michel on 26/01/15.
 */
public interface ITexturesSetFromResIdsResourceInfo<TOneRawResTexture extends IOneResTexture>  extends ITexturesSetResourceInfo<TOneRawResTexture> {
    Context getContext();
}
