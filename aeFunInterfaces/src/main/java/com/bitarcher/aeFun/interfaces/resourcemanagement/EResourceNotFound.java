package com.bitarcher.aeFun.interfaces.resourcemanagement;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;

/**
 * Created by michel on 08/01/15.
 */
public class EResourceNotFound extends RuntimeException {
    public EResourceNotFound(IResourceInfo resourceInfo, String resourceManagerContent) {
        super(String.format("Resource %s(%x) of type %s not found ; %s", resourceInfo.getName(), resourceInfo.hashCode(), resourceInfo.getClass().toString(), resourceManagerContent));
    }
}
