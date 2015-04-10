package com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo;

import com.bitarcher.aeFun.interfaces.resourcemanagement.Access.IAssetsBase;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneAssetTexture;

/**
 * Created by michel on 12/01/15.
 */
public interface ITexturesSetFromAssetResourceInfo<TOneAssetTexture extends IOneAssetTexture>  extends ITexturesSetResourceInfo<TOneAssetTexture>, IAssetsBase{
}
