package com.bitarcher.aeFun.widgetLayout.layouts.tools;

import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.widgets.Tools.IEnableSetterEntity;

import org.andengine.entity.Entity;
import org.andengine.util.adt.color.Color;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 08/04/15.
 */
public class RadioSymbol extends Entity implements IEnableSetterEntity {

    RadioButtonClickableEntity radioButtonClickableEntity;
    DiskOrXGon radioSymbol;

    public RadioSymbol(RadioButtonClickableEntity radioButtonClickableEntity, float x, float y, float width, float height) {
        super(x, y, width, height);

        this.radioButtonClickableEntity = radioButtonClickableEntity;

        this.radioSymbol = new DiskOrXGon(width / 2, height / 2, width, height, 10,
            radioButtonClickableEntity.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
        this.setColor();
        this.attachChild(this.radioSymbol);
    }

    void setColor()
    {
        Color color;
        ITheme theme = this.radioButtonClickableEntity.checkableLayout.getWidget().getTheme();

        if(this.radioButtonClickableEntity.checkableLayout.getWidget().isEnabled())
        {
            color = theme.getWidgetSections().getRadioButtonSection().getActivatedColor2();
        }
        else
        {
            color = theme.getWidgetSections().getRadioButtonSection().getDisabledColor2();
        }

        this.radioSymbol.setColor(color);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.setColor();
    }
}

