package com.bitarcher.aeFun.widgetToolkit.widget;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ENoLayoutFound;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.ICheckButtonContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.ICheckButton;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.LabeledCheckable;

/**
 * Created by michel on 20/03/15.
 */
public final class CheckButton extends LabeledCheckable<ICheckButtonContext> implements ICheckButton {
    public CheckButton(ITheme theme, float pX, float pY, float pWidth, float pHeight) {
        super(theme, pX, pY, pWidth, pHeight);

        if(this.getLayout() != null)
        {
            this.getLayout().onInit();
            this.connectToLayoutClickableEntity();
        }
        else
        {
            throw new ENoLayoutFound(this);
        }
    }
}
