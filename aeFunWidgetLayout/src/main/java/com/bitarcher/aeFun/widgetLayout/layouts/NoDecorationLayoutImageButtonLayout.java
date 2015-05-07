package com.bitarcher.aeFun.widgetLayout.layouts;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.layout.INoLayoutDecorationImageButtonLayout;
import com.bitarcher.aeFun.interfaces.gui.widgets.IImageButton;

/**
 * Created by michel on 07/05/15.
 */
public class NoDecorationLayoutImageButtonLayout extends ImageButtonLayout implements INoLayoutDecorationImageButtonLayout {
    public NoDecorationLayoutImageButtonLayout(IImageButton imageButton) {
        super(imageButton);

        this.setLayoutDecorationEnabled(false);
    }
}
