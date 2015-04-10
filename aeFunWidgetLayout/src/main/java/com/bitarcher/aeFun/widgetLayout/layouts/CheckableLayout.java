package com.bitarcher.aeFun.widgetLayout.layouts;

import com.bitarcher.aeFun.interfaces.basicioc.IClickableEntity;
import com.bitarcher.aeFun.interfaces.gui.theme.context.ICheckableContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.ICheckableLayout;
import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.interfaces.mvc.ILabeled;
import com.bitarcher.aeFun.widgetLayout.layouts.tools.CheckClickableEntityBase;
import com.bitarcher.aeFun.widgetLayout.layouts.tools.CheckableContext;

import org.andengine.entity.Entity;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.DrawType;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 20/03/15.
 */
public abstract class CheckableLayout<TCheckableContext extends ICheckableContext> extends CheckableContext
        implements ICheckableLayout<TCheckableContext>, ICheckableContext {
    IWidget<TCheckableContext> checkableWidget;
    Entity textLayer;
    CheckClickableEntityBase<TCheckableContext> checkClickableEntity;

    @Override
    protected void doSizeAndPadding() {

        this.checkClickableEntity.doSizeAndPadding();

        ILabeled labeled = (ILabeled)this.checkableWidget;
        this.setText(labeled.getTranslatedLabel());
    }

    @Override
    public void setChecked(boolean checked) {
        this.checkClickableEntity.setChecked(checked);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.setEnableColors();
    }

    public CheckableLayout(IWidget<TCheckableContext> checkableWidget) {
        this.checkableWidget = checkableWidget;
    }

    void setEnableColors()
    {
        this.checkClickableEntity.setEnableColors();
    }

    @Override
    public IWidget<TCheckableContext> getWidget() {
        return this.checkableWidget;
    }

    protected abstract CheckClickableEntityBase<TCheckableContext> getNewCheckClickableEntity();

    @Override
    public void onPopulate() {
        this.textLayer = new Entity();
        this.checkableWidget.attachChild(this.textLayer);


        //this.checkClickableEntity = new CheckButtonClickableEntity(this);
        this.checkClickableEntity = this.getNewCheckClickableEntity();
        this.checkableWidget.attachChild(this.checkClickableEntity);
        this.checkClickableEntity.onPopulate();
    }

    void setText(String label) {

        this.textLayer.detachChildren();

        if (!(label.isEmpty()))
        {
            float midWidth = this.checkableWidget.getWidth() / 2;
            float midHeight = this.checkableWidget.getHeight() / 2;

            Font font = this.checkableWidget.getTheme().getWidgetSections().getCheckButtonSection().getFont();
            Text text = new Text(midWidth, midHeight, font, label, this.checkableWidget.getTheme().getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager(), DrawType.DYNAMIC);

            this.textLayer.attachChild(text);
        }
    }

    @Override
    public void onInit() {
        ILabeled labeled = (ILabeled)this.checkableWidget;
        this.setText(labeled.getTranslatedLabel());
        this.setEnableColors();
    }

    @Override
    public TCheckableContext getContext() {
        return (TCheckableContext)this;
    }

    @Override
    public void pushResourceRequirements() {

    }

    @Override
    public void popResourceRequirements() {

    }

    @Override
    public void setTranslatedLabel(String translatedLabel) {
        this.setText(translatedLabel);
    }

    @Override
    public IClickableEntity getClickableEntity() {

        if(this.checkClickableEntity == null)
        {
            throw new RuntimeException("Clickable rectangle not initialised yet");
        }

        return this.checkClickableEntity;
    }
}
