package com.bitarcher.aeFun.interfaces.mvc;

import com.bitarcher.aeFun.interfaces.geometry.IAspectRatioOwner;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;

/**
 * Created by michel on 20/01/15.
 */
public interface IImage extends IAspectRatioOwner{
    ITexturesSetResourceInfo getTextureSetResourceInfo();
    String getTextureName();
}

