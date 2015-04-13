package com.bitarcher.aeFun.widgetLayout.layouts;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import android.opengl.GLES20;
import android.util.Log;

import com.bitarcher.aeFun.interfaces.geometry.ISize;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IAnalogOnScreenControlContext;
import com.bitarcher.aeFun.interfaces.gui.theme.layout.IAnalogOnScreenControlLayout;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IAnalogOnScreenControlSection;
import com.bitarcher.aeFun.interfaces.gui.widgets.IAnalogOnScreenControl;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceInfoListGotter;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.ResourceInfo.IResourceInfo;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michel on 12/04/15.
 */
public class AnalogOnScreenControlLayout implements IAnalogOnScreenControlLayout, IAnalogOnScreenControlContext, IResourceInfoListGotter, AnalogOnScreenControl.IAnalogOnScreenControlListener {
    IAnalogOnScreenControl analogOnScreenControlWidget;
    AnalogOnScreenControl aeAnalogOnScreenControl;
    AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener;

    @Override
    public void setAnalogOnScreenControlListener(AnalogOnScreenControl.IAnalogOnScreenControlListener analogOnScreenControlListener) {
        this.analogOnScreenControlListener = analogOnScreenControlListener;
    }

    @Override
    public List<IResourceInfo> getResourceInfoList() {
        ArrayList<IResourceInfo> retval = new ArrayList<>();

        IAnalogOnScreenControlSection section = this.getWidget().getTheme().getWidgetSections().getAnalogOnScreenControlSection();

        retval.add(section.getBaseImage().getTextureSetResourceInfo());
        retval.add(section.getKnobImage().getTextureSetResourceInfo());

        return retval;
    }

    public AnalogOnScreenControlLayout(IAnalogOnScreenControl analogOnScreenControlWidget) {
        this.analogOnScreenControlWidget = analogOnScreenControlWidget;


    }

    @Override
    public void setEnabled(boolean enabled) {
        // TODO
    }

    @Override
    public IAnalogOnScreenControl getWidget() {
        return this.analogOnScreenControlWidget;
    }

    @Override
    public void onPopulate() {


    }

    @Override
    public void onInit() {

    }

    @Override
    public IAnalogOnScreenControlContext getContext() {
        return this;
    }

    @Override
    public void setPadding(float padding) {
        this.computeAndSetSize();
    }

    @Override
    public void pushResourceRequirements() {
        this.getWidget().getTheme().getThemeManager().getResourceManager().pushRequirement(this);

        ITheme theme = this.getWidget().getTheme();
        IResourceManager resourceManager = theme.getThemeManager().getResourceManager();
        IAnalogOnScreenControlSection section = theme.getWidgetSections().getAnalogOnScreenControlSection();

        ITextureRegion baseTextureRegion = resourceManager.getTextureRegionFromTextureSetByName(
                section.getBaseImage().getTextureSetResourceInfo(),
                section.getBaseImage().getTextureName());

        ITextureRegion knobTextureRegion = resourceManager.getTextureRegionFromTextureSetByName(
                section.getKnobImage().getTextureSetResourceInfo(),
                section.getKnobImage().getTextureName());


        Engine engine =resourceManager.getEngine();

        this.aeAnalogOnScreenControl = new AnalogOnScreenControl(this.getWidget().getWidth() / 2, this.getWidget().getHeight() / 2,
                engine.getCamera(), baseTextureRegion,
                knobTextureRegion,  0.1f, 200,
                engine.getVertexBufferObjectManager(),
                this
        );

        this.getWidget().attachChild(this.aeAnalogOnScreenControl);

        this.aeAnalogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.aeAnalogOnScreenControl.getControlBase().setAlpha(0.5f);
        /*this.aeAnalogOnScreenControl.getControlBase().setScaleCenter(0, 128);
        this.aeAnalogOnScreenControl.getControlBase().setScale(1.25f);
        this.aeAnalogOnScreenControl.getControlKnob().setScale(1.25f);*/
        this.aeAnalogOnScreenControl.refreshControlKnobPosition();

        engine.getScene().setChildScene(this.aeAnalogOnScreenControl);
    }

    @Override
    public void popResourceRequirements() {
        this.getWidget().detachChild(this.aeAnalogOnScreenControl);
        this.aeAnalogOnScreenControl = null;

        this.getWidget().getTheme().getThemeManager().getResourceManager().popRequirement(this);
    }

    @Override
    public void setSize(ISize size) {
        this.computeAndSetSize();
    }

    void computeAndSetSize()
    {
        // TODO maybe


    }

    @Override
    public void onControlClick(AnalogOnScreenControl pAnalogOnScreenControl) {
        if(this.analogOnScreenControlListener != null)
        {
            this.analogOnScreenControlListener.onControlClick(pAnalogOnScreenControl);
        }
        else
        {
            Log.d("aeFun.widgetLayout", "No AnalogOnScreenControl.IAnalogOnScreenControlListener set in layout");
        }
    }

    @Override
    public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {
        if(this.analogOnScreenControlListener != null)
        {
            this.analogOnScreenControlListener.onControlChange(pBaseOnScreenControl, pValueX, pValueY);
        }
        else
        {
            Log.d("aeFun.widgetLayout", "No AnalogOnScreenControl.IAnalogOnScreenControlListener set in layout");
        }
    }
}

