package com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 02/03/15.
 */
public interface IOriginalWidthAndHeightResetable {

    // some widgets uses the original width and height to apply a scale factor on children on resize
    // for some reasons, for example if the wanted initial width and height is not known, one may want
    // to use the following method
    void resetOriginalWidthAndHeight(float originalWidth, float originalHeight);
}
