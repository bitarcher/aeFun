package com.bitarcher.aeFun.widgetToolkit.widget;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.widgets.ICheckable;
import com.bitarcher.aeFun.interfaces.gui.widgets.ICheckableListener;
import com.bitarcher.aeFun.interfaces.gui.widgets.IRadioButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.IRadioButtonGroup;

import java.util.ArrayList;

/**
 * Created by michel on 08/04/15.
 */
public class RadioButtonGroup implements IRadioButtonGroup, ICheckableListener {

    ArrayList<IRadioButton> radioButtonArrayList = new ArrayList<>();

    @Override
    public void onCheckedChanged(ICheckable checkable, boolean isChecked) {
        if(isChecked)
        {
            IRadioButton previouslyCheckedRadioButton = null;

            for(IRadioButton radioButton : radioButtonArrayList)
            {
                if(radioButton.isChecked() && radioButton != checkable)
                {
                    previouslyCheckedRadioButton = radioButton;
                    break;
                }
            }

            if(previouslyCheckedRadioButton != null)
            {
                previouslyCheckedRadioButton.setChecked(false);
            }
        }
    }

    @Override
    public void addRadioButton(IRadioButton radioButton) {
        this.radioButtonArrayList.add(radioButton);
        radioButton.addCheckableListener(this);
    }

    @Override
    public void removeRadioButton(IRadioButton radioButton) {
        this.radioButtonArrayList.remove(radioButton);
        radioButton.removeCheckableListener(this);
    }
}
