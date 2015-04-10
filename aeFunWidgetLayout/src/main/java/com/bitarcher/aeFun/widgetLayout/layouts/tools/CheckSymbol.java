package com.bitarcher.aeFun.widgetLayout.layouts.tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.widgets.Tools.IEnableSetterEntity;

import org.andengine.entity.Entity;
import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 08/04/15.
 */
public class CheckSymbol extends Entity implements IEnableSetterEntity {

    CheckButtonClickableEntity checkButtonClickableEntity;
    com.bitarcher.aeFun.geometry.primitives.CheckSymbol checkSymbol;

    public CheckSymbol(CheckButtonClickableEntity checkButtonClickableEntity, float x, float y, float width, float height) {
        super(x, y, width, height);

        this.checkButtonClickableEntity = checkButtonClickableEntity;

        this.checkSymbol = new com.bitarcher.aeFun.geometry.primitives.CheckSymbol(width / 2, height / 2, width, height,
            checkButtonClickableEntity.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
        this.setColor();
        this.attachChild(this.checkSymbol);
    }

    void setColor()
    {
        Color color;
        ITheme theme = this.checkButtonClickableEntity.checkableLayout.getWidget().getTheme();

        if(this.checkButtonClickableEntity.checkableLayout.getWidget().isEnabled())
        {
            color = theme.getWidgetSections().getCheckButtonSection().getActivatedColor2();
        }
        else
        {
            color = theme.getWidgetSections().getCheckButtonSection().getDisabledColor2();
        }

        this.checkSymbol.setColor(color);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.setColor();
    }
}
