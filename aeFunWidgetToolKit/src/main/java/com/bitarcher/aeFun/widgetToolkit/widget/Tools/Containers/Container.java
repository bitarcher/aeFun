package com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.IContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.IContainer;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.IContainerListener;
import com.bitarcher.aeFun.widgetToolkit.widget.Widget;

import org.andengine.entity.IEntity;

import java.util.ArrayList;

/**
 * Created by michel on 03/03/15.
 */
public abstract class Container<TContext extends IContext> extends Widget<TContext> implements IContainer<TContext> {

    ArrayList<IContainerListener> containerListenerArrayList = new ArrayList<>();

    public Container(ITheme theme, float pX, float pY, float pWidth, float pHeight) {
        super(theme, pX, pY, pWidth, pHeight);
    }

    @Override
    public void attachChild(IWidget widget) {
        this.doAttachWidget(widget);
    }

    protected void entityAttachChild(IEntity entity)
    {
        super.attachChild(entity);
    }

    protected void entityDetachChild(IEntity entity)
    {
        super.detachChild(entity);
    }

    abstract protected void doAttachWidget(IWidget widget);

    @Override
    public void addContainerListener(IContainerListener containerListener) {
        this.containerListenerArrayList.add(containerListener);
    }

    @Override
    public void removeContainerListener(IContainerListener containerListener) {
        this.containerListenerArrayList.remove(containerListener);
    }

    protected void raiseChildrenPositionRecomputed()
    {
        for(IContainerListener containerListener : this.containerListenerArrayList)
        {
            containerListener.onChildrenPositionRecomputed(this);
        }
    }

    abstract protected void doDetachChild(IWidget widget);

    @Override
    public void detachChild(IWidget widget)
    {
        this.doDetachChild(widget);
    }

    @Override
    public void dispose() {
        super.dispose();

        this.containerListenerArrayList.clear();
    }

    @Override
    protected void onSizeChanged() {
        super.onSizeChanged();

        this.recomputeAllContainedPositionAndSize();
    }

    @Override
    protected void onPaddingChanged() {
        super.onPaddingChanged();

        this.recomputeAllContainedPositionAndSize();
    }

    @Override
    protected void onPositionChanged() {
        super.onPositionChanged();

        this.recomputeAllContainedPositionAndSize();
    }

    protected abstract void recomputeAllContainedPositionAndSize();
}

