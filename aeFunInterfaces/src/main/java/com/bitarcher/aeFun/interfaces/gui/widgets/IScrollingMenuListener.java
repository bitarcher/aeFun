/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.gui.widgets;

import com.bitarcher.aeFun.interfaces.mvc.IImageAndLabeled;

/**
 * Created by michel on 23/02/15.
 */
public interface IScrollingMenuListener {
    void onItemClicked(IScrollingMenu scrollingMenu, IImageAndLabeled imagedAndLabeled);
}
