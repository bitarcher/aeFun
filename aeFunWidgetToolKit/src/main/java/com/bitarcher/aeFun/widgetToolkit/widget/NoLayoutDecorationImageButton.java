package com.bitarcher.aeFun.widgetToolkit.widget;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.widgets.INoLayoutDecorationImageButton;
import com.bitarcher.aeFun.interfaces.mvc.IImage;

/**
 * Created by michel on 07/05/15.
 */
public class NoLayoutDecorationImageButton extends ImageButton implements INoLayoutDecorationImageButton {
    public NoLayoutDecorationImageButton(ITheme theme, float pX, float pY, float pWidth, float pHeight, IImage image) {
        super(theme, pX, pY, pWidth, pHeight, image);
    }
}
