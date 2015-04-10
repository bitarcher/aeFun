package com.bitarcher.aeFun.widgetLayout.layouts;


import com.bitarcher.aeFun.interfaces.gui.theme.context.IRadioButtonContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.IRadioButtonLayout;
import com.bitarcher.aeFun.interfaces.gui.widgets.IRadioButton;
import com.bitarcher.aeFun.widgetLayout.layouts.tools.CheckClickableEntityBase;
import com.bitarcher.aeFun.widgetLayout.layouts.tools.RadioButtonClickableEntity;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 20/03/15.
 */
public class RadioButtonLayout extends CheckableLayout<IRadioButtonContext> implements IRadioButtonLayout, IRadioButtonContext {
    public RadioButtonLayout(IRadioButton radioButton) {
        super(radioButton);
    }

    @Override
    protected CheckClickableEntityBase<IRadioButtonContext> getNewCheckClickableEntity() {
        return new RadioButtonClickableEntity(this);
    }
}
