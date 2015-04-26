package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.drawables.animatedMeshed.trees.WindElasticFirFast;
import com.bitarcher.aeFun.drawables.animatedMeshed.trees.WindElasticFirPretty;
import com.bitarcher.aeFun.drawables.animatedMeshed.trees.WindElasticTree1;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;
import com.bitarcher.aeFun.widgetToolkit.widget.Label;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class WindElasticTreeScene extends ManagedGameScene {

    BannerCtrl bannerCtrl;
    WindElasticTree1 windElasticTree1;
    WindElasticFirFast windElasticTree2;
    WindElasticFirPretty windElasticTree3;
    //TexturedWindElasticTree treeB;
    float windStrength = 0;
    Label windStrengthLabel;
    double totalElapsedTime = 0;

	public WindElasticTreeScene(ITSceneManager sceneManager) {
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
        Camera camera = resourceManager.getEngine().getCamera();
        VertexBufferObjectManager vertexBufferObjectManager = theme.getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager();

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() * 5 / 6, camera.getWidth(), camera.getHeight() / 3, "Wind elastic tree 1");
        this.attachChild(this.bannerCtrl); // two columns span


        Font textFont = theme.getFontThemeSection().getFont(EnumFontSize.Medium);

        this.windElasticTree1 = new WindElasticTree1(150, 150, 200, 300, vertexBufferObjectManager);
        //this.windElasticTree1.setWindSide(EnumSide.Left);
        this.attachChild(windElasticTree1);


        this.windElasticTree2 = new WindElasticFirFast(300, 150, 200, 300, vertexBufferObjectManager);
        this.attachChild(this.windElasticTree2);

        this.windElasticTree3 = new WindElasticFirPretty(450, 150, 200, 300, vertexBufferObjectManager);
        this.attachChild(this.windElasticTree3);

        //this.treeB = new TexturedWindElasticTree(resourceManager, 450, 150, 200, 300, 0, EnumSide.Right, 1);
        //this.attachChild(this.treeB);


        this.windStrengthLabel = new Label(theme, 400, 300, 400, 100, "");
        this.attachChild(this.windStrengthLabel);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.totalElapsedTime += pSecondsElapsed;

        //this.windStrength = 0;
        this.windStrength = (float)(Math.abs(Math.cos(Math.cos(this.totalElapsedTime) * Math.PI))) / 3;
        this.windElasticTree1.setWindStrength(this.windStrength);
        this.windElasticTree2.setWindStrength(this.windStrength);
        this.windElasticTree3.setWindStrength(this.windStrength);

        //this.treeB.setWindStrength(this.windStrength);

        this.windStrengthLabel.setTranslatedLabel(String.format("S = %.01f", this.windStrength));
    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }


}
