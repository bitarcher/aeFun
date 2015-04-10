package com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneAssetTexture;

/**
 * Created by michel on 10/01/15.
 */
public abstract class OneAssetTexture extends OneTexture implements IOneAssetTexture {

    protected String filename;


    @Override
    public String getFilename() {
        return this.filename;
    }

    public OneAssetTexture(String name, String filename) {
        super(name);
        this.filename = filename;
    }
}
