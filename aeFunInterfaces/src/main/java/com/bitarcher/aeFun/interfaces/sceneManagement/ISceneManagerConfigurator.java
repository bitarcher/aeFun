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
public interface ISceneManagerConfigurator<TResourceManager extends IResourceManager, TTheme extends ITheme, TMainMenu extends IMainMenu,
        TOptionsLayer extends IOptionsLayer> {
    TResourceManager getNewResourceManager();
    TTheme getNewTheme(IThemeManager themeManager);
    TMainMenu getNewMainMenu(ITSceneManager<TResourceManager, TTheme, TMainMenu, TOptionsLayer> sceneManager, TTheme theme, TResourceManager resourceManager);
    TOptionsLayer getNewOptionsLayer(ITSceneManager<TResourceManager, TTheme, TMainMenu, TOptionsLayer> sceneManager, TTheme theme, TResourceManager resourceManager, TMainMenu  mainMenu);
}
