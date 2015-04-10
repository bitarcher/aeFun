package com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo;


/**
 * Created by michel on 13/01/15.
 */
public interface IAnimationResourceInfo extends  IResourceInfo, ITextureAtlasSpecific{


    String getAssetsBase();
    String getFilename();
    int getNumOfColumns();
    int getNumOfRows();
    float getInitialX();
    float getInitialY();
    boolean isEnableDithering();
}
