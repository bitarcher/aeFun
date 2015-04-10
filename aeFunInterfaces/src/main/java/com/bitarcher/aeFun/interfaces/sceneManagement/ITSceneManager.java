/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.sceneManagement;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.IThemeManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;

/**
 * Created by michel on 02/02/15.
 */
public interface ITSceneManager<TResourceManager extends IResourceManager, TTheme extends ITheme, TMainMenu extends IMainMenu, TOptionsLayer extends IOptionsLayer> {

    TResourceManager getResourceManager();
    TTheme getTheme();
    IThemeManager getThemeManager();
    TMainMenu getMainMenu();
    TOptionsLayer getOptionsLayer();

    void showScene(final IManagedScene pManagedScene);
    void showMainMenu();
    void showOptionsLayer(final boolean pSuspendCurrentSceneUpdates);
    void showLayer(final IManagedLayer pLayer, final boolean pSuspendSceneDrawing, final boolean pSuspendSceneUpdates, final boolean pSuspendSceneTouchEvents);
    void hideLayer();

    IManagedScene getCurrentScene();

    void setCurrentScene(IManagedScene mCurrentScene);

    boolean isLayerShown();

    void setLayerShown(boolean isLayerShown);

    IManagedLayer getCurrentLayer();

    void setCurrentLayer(IManagedLayer currentLayer);
}

