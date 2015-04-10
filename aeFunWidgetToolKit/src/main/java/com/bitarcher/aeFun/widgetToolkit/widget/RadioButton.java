package com.bitarcher.aeFun.widgetToolkit.widget;

import com.bitarcher.aeFun.interfaces.gui.theme.ENoLayoutFound;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IRadioButtonContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.IRadioButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.IRadioButtonGroup;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.LabeledCheckable;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 20/03/15.
 */
public final class RadioButton extends LabeledCheckable<IRadioButtonContext> implements IRadioButton {

    IRadioButtonGroup radioButtonGroup;

    public RadioButton(ITheme theme, float pX, float pY, float pWidth, float pHeight, IRadioButtonGroup radioButtonGroup) {
        super(theme, pX, pY, pWidth, pHeight);

        this.radioButtonGroup = radioButtonGroup;
        this.radioButtonGroup.addRadioButton(this);

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

    @Override
    public IRadioButtonGroup getRadioButtonGroup() {
        return this.radioButtonGroup;
    }

    @Override
    protected boolean shouldReverseCheckOnClickIfAlreadyChecked() {
        return false;
    }
}
