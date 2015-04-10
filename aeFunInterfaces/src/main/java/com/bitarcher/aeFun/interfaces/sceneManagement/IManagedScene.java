/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.sceneManagement;

import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;

/**
 * Created by michel on 03/02/15.
 */
public interface IManagedScene extends IScene {
    ITSceneManager getSceneManager();

    boolean getHasLoadingScreen();

    float getMinLoadingScreenTime();

    float getElapsedLoadingScreenTime();

    void setElapsedLoadingScreenTime(float elapsedLoadingScreenTime);

    boolean isLoaded();

    void setLoaded(boolean isLoaded);




    // Called by the Scene Manager. It calls onLoadScene if loading is needed, sets the isLoaded status, and pauses the scene while it's not shown.
    void onLoadManagedScene();

    // Called by the Scene Manager. It calls onUnloadScene if the scene has been previously loaded and sets the isLoaded status.
    void onUnloadManagedScene();

    // Called by the Scene Manager. It unpauses the scene before showing it.
    void onShowManagedScene();

    // Called by the Scene Manager. It pauses the scene before hiding it.
    void onHideManagedScene();

    // Methods to Override in the subclasses.
    IScene onLoadingScreenLoadAndShown();

    void onLoadingScreenUnloadAndHidden();

    void onLoadScene();

    void onShowScene();

    void onHideScene();

    void onUnloadScene();
}
