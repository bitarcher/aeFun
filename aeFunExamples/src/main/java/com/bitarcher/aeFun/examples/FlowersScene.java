package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses.TimberedHouse0;
import com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses.TimberedHouse1;
import com.bitarcher.aeFun.drawables.animatedMeshed.architecture.medieval.houses.TimberedHouse2;
import com.bitarcher.aeFun.drawables.animatedMeshed.nature.flowers.WindElasticFlower0;
import com.bitarcher.aeFun.drawables.animatedMeshed.nature.flowers.WindElasticFlower1;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;
import com.bitarcher.aeFun.widgetToolkit.widget.Label;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class FlowersScene extends ManagedGameScene{

    BannerCtrl bannerCtrl;
    float windStrength = 0;
    Label windStrengthLabel;
    double totalElapsedTime = 0;



    WindElasticFlower0 windElasticFlower0;
    WindElasticFlower1 windElasticFlower1;

	public FlowersScene(ITSceneManager sceneManager) {
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
        this.setBackground(new Background(0.3f, 1, 0.3f));

        ITheme theme = this.getSceneManager().getTheme();
        IResourceManager resourceManager = theme.getThemeManager().getResourceManager();
        Camera camera = theme.getThemeManager().getResourceManager().getEngine().getCamera();
        VertexBufferObjectManager vertexBufferObjectManager = theme.getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager();

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() * 5 / 6, camera.getWidth(), camera.getHeight() / 3, "Flowers");
        this.attachChild(this.bannerCtrl); // two columns span



        this.windElasticFlower0 = new WindElasticFlower0(100, 100, 200, 330, vertexBufferObjectManager);
        this.attachChild(this.windElasticFlower0);

        this.windElasticFlower1 = new WindElasticFlower1(300, 100, 200, 330, vertexBufferObjectManager);
        this.attachChild(this.windElasticFlower1);

        this.windStrengthLabel = new Label(theme, 400, 300, 400, 100, "");
        this.attachChild(this.windStrengthLabel);
    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.totalElapsedTime += pSecondsElapsed;

        //this.windStrength = 0;
        this.windStrength = (float)(Math.abs(Math.cos(Math.cos(this.totalElapsedTime) * Math.PI))) / 3;
        this.windElasticFlower0.setWindStrength(this.windStrength);
        this.windElasticFlower1.setWindStrength(this.windStrength);

        //this.treeB.setWindStrength(this.windStrength);

        this.windStrengthLabel.setTranslatedLabel(String.format("S = %.01f", this.windStrength));
    }

}
