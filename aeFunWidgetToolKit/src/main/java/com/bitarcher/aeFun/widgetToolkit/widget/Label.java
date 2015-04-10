package com.bitarcher.aeFun.widgetToolkit.widget;


import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.geometry.IAlignedListener;
import com.bitarcher.aeFun.interfaces.gui.theme.ENoLayoutFound;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.ILabelContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.ILabel;
import com.bitarcher.aeFun.interfaces.mvc.ILabeledListener;


import java.util.ArrayList;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**-
 * Created by michel on 22/01/15.
 */
public final class Label extends Widget<ILabelContext> implements ILabel {
    protected String translatedLabel;
    ArrayList<ILabeledListener> labeledListenerArrayList = new ArrayList<>();
    EnumAlignStyle alignStyle = EnumAlignStyle.Center;
    ArrayList<IAlignedListener> alignedListenerArrayList = new ArrayList<>();

    @Override
    public void setAlignStyle(EnumAlignStyle alignStyle) {
        this.alignStyle = alignStyle;

        this.onAlignStyleChanged();

        for(IAlignedListener dockStyledListener : this.alignedListenerArrayList)
        {
            dockStyledListener.onAlignStyleChanged(this, this.alignStyle);
        }
    }


    protected void onAlignStyleChanged()
    {
        if(this.getLayout() != null)
        {
            this.getLayout().getContext().setAlignStyle(this.alignStyle);
        }
    }

    @Override
    public void addAlignStyledListener(IAlignedListener alignedListener) {
        this.alignedListenerArrayList.add(alignedListener);
    }

    @Override
    public void removeAlignStyledListener(IAlignedListener alignedListener) {
        this.alignedListenerArrayList.remove(alignedListener);
    }

    @Override
    public EnumAlignStyle getAlignStyle() {
        return this.alignStyle;
    }





    public Label(ITheme theme, float pX, float pY, float pWidth, float pHeight, String translatedLabel) {
        super(theme, pX, pY, pWidth, pHeight);
        this.translatedLabel = translatedLabel;


        if(this.getLayout() != null)
        {
            this.getLayout().onInit();
        }
        else
        {
            throw new ENoLayoutFound(this);
        }
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
    public void setTranslatedLabel(String translatedLabel) {
        this.translatedLabel = translatedLabel;

        for(ILabeledListener labeledListener : this.labeledListenerArrayList)
        {
            labeledListener.onLabelChanged(this);
        }

        this.onTranslatedLabelChanged(translatedLabel);
    }

    protected void onTranslatedLabelChanged(String translatedLabel)
    {
        if(this.getLayout() != null)
        {
            this.getLayout().getContext().setTranslatedLabel(this.getTranslatedLabel());
        }

    }

    @Override
    public String getTranslatedLabel() {
        return this.translatedLabel;
    }

    @Override
    public void dispose() {
        super.dispose();

        this.labeledListenerArrayList.clear();
        this.alignedListenerArrayList.clear();
    }
}
