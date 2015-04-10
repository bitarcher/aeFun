package com.bitarcher.aeFun.resourceManagement.ResourcesInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IBitmapTexturesSetFromAssetResourceInfo;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;

/**
 * Created by michel on 08/01/15.
 */
public abstract class ResourceInfo implements IResourceInfo {
    String name;

    protected ResourceInfo(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public boolean isSimilar(IResourceInfo ressourceInfo) {
        boolean retval = true;

        if(ressourceInfo instanceof IBitmapTexturesSetFromAssetResourceInfo)
        {
            retval &= this.name == ((IBitmapTexturesSetFromAssetResourceInfo) ressourceInfo).getName();
        }
        else
        {
            retval = false;
        }

        return retval;
    }

    @Override
    public int getHashCode() {
        return this.name.hashCode();
    }

}
