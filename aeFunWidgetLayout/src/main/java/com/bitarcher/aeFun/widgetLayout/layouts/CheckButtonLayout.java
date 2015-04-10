package com.bitarcher.aeFun.widgetLayout.layouts;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.context.ICheckButtonContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.ICheckButtonLayout;
import com.bitarcher.aeFun.interfaces.gui.widgets.ICheckButton;
import com.bitarcher.aeFun.widgetLayout.layouts.tools.CheckButtonClickableEntity;
import com.bitarcher.aeFun.widgetLayout.layouts.tools.CheckClickableEntityBase;

/**
 * Created by michel on 20/03/15.
 */
public class CheckButtonLayout extends CheckableLayout<ICheckButtonContext> implements ICheckButtonLayout, ICheckButtonContext {
    public CheckButtonLayout(ICheckButton checkButton) {
        super(checkButton);
    }

    @Override
    protected CheckClickableEntityBase<ICheckButtonContext> getNewCheckClickableEntity() {
        return new CheckButtonClickableEntity(this);
    }
}
