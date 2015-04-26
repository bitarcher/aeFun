package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses.TimberedHouse0;
import com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses.TimberedHouse1;
import com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses.TimberedHouse2;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class HousesScene extends ManagedGameScene{

    BannerCtrl bannerCtrl;

    TimberedHouse0 timberedHouse0;
    TimberedHouse1 timberedHouse1;
    TimberedHouse2 timberedHouse2;

	public HousesScene(ITSceneManager sceneManager) {
        super(sceneManager, 0); // no loading screen
	}

    @Override
    public void onShowScene() {

    }

    @Override
    public void onHideScene() {

    }



    // No loading screen means no reason to use the following methods.
	@Override
	public IScene onLoadingScreenLoadAndShown() {
		return null;
	}
	@Override
	public void onLoadingScreenUnloadAndHidden() {
	}

	@Override
    public void onLoadScene() {
        this.setBackgroundEnabled(true);
        this.setBackground(new Background(1, 1, 1));

        ITheme theme = this.getSceneManager().getTheme();
        IResourceManager resourceManager = theme.getThemeManager().getResourceManager();
        Camera camera = theme.getThemeManager().getResourceManager().getEngine().getCamera();
        VertexBufferObjectManager vertexBufferObjectManager = theme.getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager();

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() * 5 / 6, camera.getWidth(), camera.getHeight() / 3, "Houses");
        this.attachChild(this.bannerCtrl); // two columns span


        this.timberedHouse0 = new TimberedHouse0(resourceManager, 100, 100, 200, 200, Color.YELLOW);
        this.attachChild(this.timberedHouse0);

        this.timberedHouse1 = new TimberedHouse1(resourceManager, 300, 100, 200, 200,  Color.YELLOW);
        this.attachChild(this.timberedHouse1);

        this.timberedHouse2 = new TimberedHouse2(resourceManager, 600, 100, 400, 200,  Color.YELLOW);
        this.attachChild(this.timberedHouse2);
    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }
}
