package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.drawables.animatedMeshed.WindElasticTree1;
import com.bitarcher.aeFun.drawables.animatedMeshed.WindMill;
import com.bitarcher.aeFun.interfaces.drawables.animatedMeshed.IWindStrength;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
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

public class WindMillScene extends ManagedGameScene implements IWindStrength {

    BannerCtrl bannerCtrl;

    float windStrength = 1;
    Label windStrengthLabel;
    double totalElapsedTime = 0;
    WindMill windMill;

	public WindMillScene(ITSceneManager sceneManager) {
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
        Camera camera = theme.getThemeManager().getResourceManager().getEngine().getCamera();
        VertexBufferObjectManager vertexBufferObjectManager = theme.getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager();

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() * 5 / 6, camera.getWidth(), camera.getHeight() / 3, "Windmill");
        this.attachChild(this.bannerCtrl); // two columns span


        Font textFont = theme.getFontThemeSection().getFont(EnumFontSize.Medium);

        this.windMill = new WindMill(250, 100, 200, 250, vertexBufferObjectManager);
        this.attachChild(this.windMill);

        this.windStrengthLabel = new Label(theme, 600, 100, 200, 80, "");
        this.attachChild(this.windStrengthLabel);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.totalElapsedTime += pSecondsElapsed;

        //this.windStrength = 0;
        this.windStrength = (float)(Math.abs(Math.cos(this.totalElapsedTime / 30)));
        this.windMill.setWindStrength(this.windStrength);



        this.windStrengthLabel.setTranslatedLabel(String.format("S = %.01f", this.windStrength));
    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }

    @Override
    public float getWindStrength() {
        return this.windStrength;
    }

    @Override
    public void setWindStrength(float windStrength) {
        this.windStrength = windStrength;
    }

}
