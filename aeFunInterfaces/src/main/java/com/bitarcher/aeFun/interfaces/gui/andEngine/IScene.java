/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.gui.andEngine;


import org.andengine.entity.IEntity;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.util.adt.list.SmartList;

/**
 * Created by michel on 03/02/15.
 */
public interface IScene extends IEntity{


    // ===========================================================
    // Getter & Setter
    // ===========================================================

    float getSecondsElapsedTotal();

    IBackground getBackground();

    void setBackground(final IBackground pBackground);

    boolean isBackgroundEnabled();

    void setBackgroundEnabled(final boolean pEnabled);

    void setOnSceneTouchListener(final IOnSceneTouchListener pOnSceneTouchListener);


    IOnSceneTouchListener getOnSceneTouchListener();

    boolean hasOnSceneTouchListener();

    void setOnAreaTouchListener(final IOnAreaTouchListener pOnAreaTouchListener);


    IOnAreaTouchListener getOnAreaTouchListener();

    boolean hasOnAreaTouchListener();

    boolean hasChildScene();

    IScene getIChildScene();

    void setChildSceneModal(final IScene pChildScene);

    void setChildScene(final IScene pChildScene);

    void setChildScene(final IScene pChildScene, final boolean pModalDraw, final boolean pModalUpdate, final boolean pModalTouch);

    void clearChildScene();

    void setOnAreaTouchTraversalBackToFront();

    void setOnAreaTouchTraversalFrontToBack();

    boolean isTouchAreaBindingOnActionDownEnabled();

    boolean isTouchAreaBindingOnActionMoveEnabled();

    void setTouchAreaBindingOnActionDownEnabled(final boolean pTouchAreaBindingOnActionDownEnabled);

    void setTouchAreaBindingOnActionMoveEnabled(final boolean pTouchAreaBindingOnActionMoveEnabled);

    boolean isOnSceneTouchListenerBindingOnActionDownEnabled();

    void setOnSceneTouchListenerBindingOnActionDownEnabled(final boolean pOnSceneTouchListenerBindingOnActionDownEnabled);


    // ===========================================================
    // Methods
    // ===========================================================

    void postRunnable(final Runnable pRunnable);

    void registerTouchArea(final ITouchArea pTouchArea);

    boolean unregisterTouchArea(final ITouchArea pTouchArea);

    boolean unregisterTouchAreas(final ITouchArea.ITouchAreaMatcher pTouchAreaMatcher);

    void clearTouchAreas();

    SmartList<ITouchArea> getTouchAreas();

    void back();
}

