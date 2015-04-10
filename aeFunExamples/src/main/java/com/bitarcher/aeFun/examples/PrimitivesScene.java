package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.geometry.primitives.CheckSymbol;
import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;

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

public class PrimitivesScene extends ManagedGameScene {

    BannerCtrl bannerCtrl;

    Text diskText;
    DiskOrXGon diskOrXGon1;
    DiskOrXGon diskOrXGon2;
    Text checkSymbolText;
    CheckSymbol checkSymbol;

	public PrimitivesScene(ITSceneManager sceneManager) {
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

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() * 5 / 6, camera.getWidth(), camera.getHeight() / 3, "Primitives");
        this.attachChild(this.bannerCtrl); // two columns span


        Font textFont = theme.getFontThemeSection().getFont(EnumFontSize.Medium);

        this.diskText = new Text(100, 50, textFont, "Disk or \"X\"gon", vertexBufferObjectManager);
        this.attachChild(this.diskText);


        // hexagon (6 fan sectors), for octogon put 8, for pentagon put 5, etc...
        this.diskOrXGon1 = new DiskOrXGon(50, 150, 100, 100, 6, vertexBufferObjectManager);
        this.attachChild(this.diskOrXGon1);

        this.diskOrXGon2 = new DiskOrXGon(50, 250, 100, 100, 20, vertexBufferObjectManager);
        this.diskOrXGon2.setColor(Color.PINK);
        this.attachChild(this.diskOrXGon2);

        this.checkSymbolText = new Text(160, 80, textFont, "Check symbol", vertexBufferObjectManager);
        this.attachChild(this.checkSymbolText);

        this.checkSymbol = new CheckSymbol(150, 200, 100, 100, vertexBufferObjectManager);
        this.checkSymbol.setColor(Color.GREEN);
        this.attachChild(this.checkSymbol);
    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }
}
