package com.bitarcher.aeFun.widgetLayout.layouts.tools;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.context.ICheckButtonContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.ICheckButtonLayout;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.ICheckableSection;
import com.bitarcher.aeFun.interfaces.gui.widgets.Tools.IEnableSetterEntity;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Rectangle;

/**
 * Created by michel on 01/04/15.
 */
public class CheckButtonClickableEntity extends CheckClickableEntityBase<ICheckButtonContext>
{
    public CheckButtonClickableEntity(ICheckButtonLayout checkButtonLayout) {
        super(checkButtonLayout);
    }

    @Override
    protected IEntity getNewEntity1(float x, float y, float width, float height) {
        return new Rectangle(x, y, width, height, this.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
    }

    @Override
    protected IEntity getNewEntity2(float x, float y, float width, float height) {
        return new Rectangle(x, y, width, height, this.checkableLayout.getWidget().getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager());
    }

    @Override
    protected IEnableSetterEntity getNewCheckSymbol(float x, float y, float width, float height) {
        return new CheckSymbol(this, x, y, width, height);
    }

    @Override
    protected ICheckableSection getCheckableWidgetSection() {
        return this.checkableLayout.getWidget().getTheme().getWidgetSections().getCheckButtonSection();
    }
}

