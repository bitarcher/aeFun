package com.bitarcher.aeFun.interfaces.gui.theme;

import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.ITexturesSetResourceInfo;

/**
 * Created by michel on 22/01/15.
 */
public interface ITextButtonSection {

    /**
     *
     * @return a textureSet (bitmap or svg) on which the following texture name will be called "normal", "pressed", "disabled"
     */
    ITexturesSetResourceInfo getTexturesSetResourceInfo();
}
