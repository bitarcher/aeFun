package com.bitarcher.aeFun.widgetLayout.porcelain;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.ICheckButtonSection;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IImageButtonSection;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IImageSection;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.ILabelSection;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IRadioButtonSection;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.ITextButtonSection;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IWidgetSections;
import com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections.DefaultCheckButtonSection;
import com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections.DefaultImageButtonSection;
import com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections.DefaultRadioButtonSection;
import com.bitarcher.aeFun.widgetLayout.porcelain.WidgetSections.DefaultTextButtonSection;

/**
 * Created by michel on 10/03/15.
 */
public class DefaultWidgetSections implements IWidgetSections {
    ITheme theme;
    ITextButtonSection textButtonSection;
    IImageButtonSection imageButtonSection;
    ICheckButtonSection checkButtonSection;
    IRadioButtonSection radioButtonSection;
    IImageSection imageSection;
    ILabelSection labelSection;


    public DefaultWidgetSections(ITheme theme) {
        this.theme = theme;
    }

    @Override
    public ITextButtonSection getTextButtonSection() {
        if(this.textButtonSection == null)
        {
            this.textButtonSection = new DefaultTextButtonSection(this.theme);
        }

        return this.textButtonSection;
    }

    @Override
    public IImageButtonSection getImageButtonSection() {
        if(this.imageButtonSection == null)
        {
            this.imageButtonSection = new DefaultImageButtonSection(this.theme);
        }

        return this.imageButtonSection;
    }

    @Override
    public ICheckButtonSection getCheckButtonSection() {
        if(this.checkButtonSection == null)
        {
            this.checkButtonSection = new DefaultCheckButtonSection(this.theme);
        }

        return this.checkButtonSection;
    }

    @Override
    public IRadioButtonSection getRadioButtonSection() {
        if(this.radioButtonSection == null)
        {
            this.radioButtonSection = new DefaultRadioButtonSection(this.theme);
        }

        return this.radioButtonSection;
    }

    @Override
    public IImageSection getImageSection() {
        return imageSection;
    }

    @Override
    public ILabelSection getLabelSection() {
        return labelSection;
    }
}

