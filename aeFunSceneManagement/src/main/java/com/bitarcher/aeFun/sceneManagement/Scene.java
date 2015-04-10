/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.sceneManagement;

import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;

/**
 * Created by michel on 04/02/15.
 */
public class Scene extends org.andengine.entity.scene.Scene implements IScene {


    @Override
    public IScene getIChildScene() {
        org.andengine.entity.scene.Scene sScene = super.getChildScene();

        if(!(sScene instanceof IScene))
        {
            throw new IncompatibleClassChangeError();
        }


        return (IScene)sScene;
    }

    @Override
    public void setChildSceneModal(IScene pChildScene) {
        super.setChildSceneModal((org.andengine.entity.scene.Scene) pChildScene);

    }

    @Override
    public void setChildScene(IScene pChildScene) {
        super.setChildScene((org.andengine.entity.scene.Scene) pChildScene);
    }

    @Override
    public void setChildScene(IScene pChildScene, boolean pModalDraw, boolean pModalUpdate, boolean pModalTouch) {
        super.setChildScene((org.andengine.entity.scene.Scene) pChildScene, pModalDraw, pModalUpdate, pModalTouch);
    }


}
