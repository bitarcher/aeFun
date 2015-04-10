/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.gui.andEngine;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;

/**
 * Created by michel on 03/02/15.
 */
public interface ICameraScene extends IScene {
    Camera getCamera();

    void setCamera(final Camera pCamera);

    void centerEntityInCamera(final IEntity pEntity);

    void centerEntityInCameraHorizontally(final IEntity pEntity);

    void centerEntityInCameraVertically(final IEntity pEntity);

}
