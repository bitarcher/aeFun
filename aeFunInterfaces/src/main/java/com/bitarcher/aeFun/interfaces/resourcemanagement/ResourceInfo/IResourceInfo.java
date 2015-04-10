package com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo;

import com.bitarcher.aeFun.interfaces.basicioc.INamed;

/**
 * Created by michel on 08/01/15.
 */
public interface IResourceInfo extends INamed{

    // FIXME not implemented we use the reference to distinguish two resource infos, so
    // delete those two following entries or implement them for all different classes
    boolean isSimilar(IResourceInfo resourceInfo);
    int getHashCode();
}
