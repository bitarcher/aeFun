package com.bitarcher.aeFun.widgetLayout.layouts.tools;

import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IRadioButtonContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.IRadioButtonLayout;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.ICheckableSection;
import com.bitarcher.aeFun.interfaces.gui.widgets.Tools.IEnableSetterEntity;

import org.andengine.entity.IEntity;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 01/04/15.
 */
public class RadioButtonClickableEntity extends CheckClickableEntityBase<IRadioButtonContext>
{
    public RadioButtonClickableEntity(IRadioButtonLayout radioButtonLayout) {
        super(radioButtonLayout);
    }

    @Override
    protected IEntity getNewEntity1(float x, float y, float width, float height) {
        return new DiskOrXGon(x, y, width, height, 12, this.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
    }

    @Override
    protected IEntity getNewEntity2(float x, float y, float width, float height) {
        return new DiskOrXGon(x, y, width, height, 11, this.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
    }

    @Override
    protected IEnableSetterEntity getNewCheckSymbol(float x, float y, float width, float height) {
        return new RadioSymbol(this, x, y, width * 0.9f, height *0.9f);
    }

    @Override
    protected ICheckableSection getCheckableWidgetSection() {
        return this.checkableLayout.getWidget().getTheme().getWidgetSections().getRadioButtonSection();
    }
}

