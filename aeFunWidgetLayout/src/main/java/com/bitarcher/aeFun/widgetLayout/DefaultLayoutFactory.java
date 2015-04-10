package com.bitarcher.aeFun.widgetLayout;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ILayout;
import com.bitarcher.aeFun.interfaces.gui.theme.ILayoutFactory;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.ICheckButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.IImage;
import com.bitarcher.aeFun.interfaces.gui.widgets.IImageButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.ILabel;
import com.bitarcher.aeFun.interfaces.gui.widgets.IRadioButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.ITextButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.widgetLayout.layouts.CheckButtonLayout;
import com.bitarcher.aeFun.widgetLayout.layouts.ImageButtonLayout;
import com.bitarcher.aeFun.widgetLayout.layouts.ImageLayout;
import com.bitarcher.aeFun.widgetLayout.layouts.LabelLayout;
import com.bitarcher.aeFun.widgetLayout.layouts.RadioButtonLayout;
import com.bitarcher.aeFun.widgetLayout.layouts.TextButtonLayout;

/**
 * Created by michel on 09/03/15.
 */
public class DefaultLayoutFactory implements ILayoutFactory {
    @Override
    public <TContext extends IContext> ILayout<TContext> make(IWidget<TContext> key) {
        ILayout<TContext> retval = null;

        if(key instanceof ITextButton)
        {
            ITextButton keyS = (ITextButton)key;
            TextButtonLayout retS = new TextButtonLayout(keyS);
            retval = (ILayout<TContext>)retS;
        }
        else if (key instanceof IImageButton)
        {
            IImageButton keyS = (IImageButton)key;
            ImageButtonLayout retS = new ImageButtonLayout(keyS);
            retval = (ILayout<TContext>)retS;
        }
        else if(key instanceof ILabel)
        {
            ILabel keyS = (ILabel)key;
            LabelLayout retS = new LabelLayout(keyS);
            retval = (ILayout<TContext>)retS;
        }
        else if(key instanceof IImage)
        {
            IImage keyS = (IImage)key;
            ImageLayout retS = new ImageLayout(keyS);
            retval = (ILayout<TContext>)retS;
        }
        else if(key instanceof ICheckButton)
        {
            ICheckButton keyS = (ICheckButton)key;
            CheckButtonLayout retS = new CheckButtonLayout(keyS);
            retval = (ILayout<TContext>)retS;
        }
        else if(key instanceof IRadioButton)
        {
            IRadioButton keyS = (IRadioButton)key;
            RadioButtonLayout retS = new RadioButtonLayout(keyS);
            retval = (ILayout<TContext>)retS;
        }

        return retval;
    }
}

