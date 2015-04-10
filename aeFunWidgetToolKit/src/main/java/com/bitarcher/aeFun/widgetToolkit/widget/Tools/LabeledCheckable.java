package com.bitarcher.aeFun.widgetToolkit.widget.Tools;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.ICheckableContext;
import com.bitarcher.aeFun.interfaces.mvc.ILabeledListener;
import com.bitarcher.aeFun.interfaces.mvc.ILabeled_Edit;

import java.util.ArrayList;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 20/03/15.
 */
public class LabeledCheckable<TCheckableContext extends ICheckableContext> extends Checkable<TCheckableContext> implements ILabeled_Edit {

    String translatedLabel = "";
    ArrayList<ILabeledListener> labeledListenerArrayList = new ArrayList<>();

    public LabeledCheckable(ITheme theme, float pX, float pY, float pWidth, float pHeight) {
        super(theme, pX, pY, pWidth, pHeight);
    }

    @Override
    public void addLabeledListener(ILabeledListener labeledListener) {
        this.labeledListenerArrayList.add(labeledListener);
    }

    @Override
    public void removeLabeledListener(ILabeledListener labeledListener) {
        this.labeledListenerArrayList.remove(labeledListener);
    }

    @Override
    public String getTranslatedLabel() {
        return translatedLabel;
    }

    @Override
    public void setTranslatedLabel(String translatedLabel) {
        this.translatedLabel = translatedLabel;

        this.onTranslatedLabelChanged();

        for(ILabeledListener labeledListener: this.labeledListenerArrayList)
        {
            labeledListener.onLabelChanged(this);
        }
    }

    protected void onTranslatedLabelChanged()
    {
        if(this.getLayout() != null)
        {
            this.getLayout().getContext().setTranslatedLabel(this.translatedLabel);
        }
    }

    @Override
    public void dispose() {
        super.dispose();

        this.labeledListenerArrayList.clear();
    }
}
