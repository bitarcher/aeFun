package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.drawables.animatedMeshed.Tools.WindElasticCompositeMeshes;
import com.bitarcher.aeFun.drawables.animatedMeshed.WindElasticTree1;
import com.bitarcher.aeFun.geometry.primitives.CheckSymbol;
import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;
import com.bitarcher.aeFun.widgetToolkit.widget.Label;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class WindElasticTreeScene extends ManagedGameScene {

    BannerCtrl bannerCtrl;
    WindElasticTree1 treeA;
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
        Camera camera = theme.getThemeManager().getResourceManager().getEngine().getCamera();
        VertexBufferObjectManager vertexBufferObjectManager = theme.getThemeManager().getResourceManager().getEngine().getVertexBufferObjectManager();

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() * 5 / 6, camera.getWidth(), camera.getHeight() / 3, "Wind elastic tree 1");
        this.attachChild(this.bannerCtrl); // two columns span


        Font textFont = theme.getFontThemeSection().getFont(EnumFontSize.Medium);

        this.treeA = new WindElasticTree1(150, 150, 200, 300, vertexBufferObjectManager);
        //this.treeA.setWindSide(EnumSide.Left);
        this.attachChild(treeA);

        this.windStrengthLabel = new Label(theme, 400, 300, 400, 100, "");
        this.attachChild(this.windStrengthLabel);
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        this.totalElapsedTime += pSecondsElapsed;

        //this.windStrength = 0;
        this.windStrength = (float)(Math.abs(Math.cos(this.totalElapsedTime)));
        this.treeA.setWindStrength(this.windStrength);

        this.windStrengthLabel.setTranslatedLabel(String.format("S = %.01f", this.windStrength));
    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }


}
