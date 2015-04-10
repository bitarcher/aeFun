/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.resourceManagement.ResourcesInfos.SubInfos;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.SubInfos.IOneTexture;

/**
 * Created by michel on 26/01/15.
 */
public abstract class OneTexture  implements IOneTexture{
    String name;


    @Override
    public String getName() {
        return this.name;
    }

    protected OneTexture(String name) {
        this.name = name;
    }
}
