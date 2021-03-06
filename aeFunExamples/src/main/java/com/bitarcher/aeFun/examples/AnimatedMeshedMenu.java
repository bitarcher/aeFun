package com.bitarcher.aeFun.examples;


import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.widgets.IButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.IButtonListener;
import com.bitarcher.aeFun.interfaces.sceneManagement.IManagedMenuScene;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedMenuScene;
import com.bitarcher.aeFun.widgetToolkit.widget.Table;
import com.bitarcher.aeFun.widgetToolkit.widget.TextButton;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class AnimatedMeshedMenu extends ManagedMenuScene implements IManagedMenuScene {
    Table table;
    BannerCtrl bannerCtrl;
    TextButton windTree1Button;
    TextButton windMillButton;
    TextButton housesButton;
    TextButton flowersButton;
    TextButton forestButton;

	public AnimatedMeshedMenu(ITSceneManager sceneManager) {
        super(sceneManager);
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

        final AnimatedMeshedMenu animatedMeshedMenu = this;
        this.setBackgroundEnabled(true);
        this.setBackground(new Background(1, 1, 1));

        ITheme theme = this.getSceneManager().getTheme();
        Camera camera = theme.getThemeManager().getResourceManager().getEngine().getCamera();
        this.table = new Table(theme, camera.getWidth() / 2,  camera.getHeight() / 2, camera.getWidth(), camera.getHeight());
        //this.table = new Table(theme, camera.getWidth() / 2,  camera.getHeight() / 3 * 2, camera.getWidth(), camera.getHeight() / 3 * 2);

        this.table.addHomogeneousColumnsAndRows(2, 4, 5);
        //this.table.addHomogeneousColumnsAndRows(2, 2, 5);

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() / 6, camera.getWidth(), camera.getHeight() / 3, "Widget gallery");
        this.table.attachChild(this.bannerCtrl, 0, 0, 2, 1); // two columns span

        this.windTree1Button = new TextButton(this.getSceneManager().getTheme(), 0, 0, 10, 10, "Wind elastic trees");
        this.table.attachChild(this.windTree1Button);
        this.windTree1Button.addButtonListener(new IButtonListener() {
            @Override
            public void onClicked(IButton button) {
                animatedMeshedMenu.getSceneManager().showScene(new WindElasticTreeScene(animatedMeshedMenu.getSceneManager()));
            }
        });


        this.windMillButton = new TextButton(this.getSceneManager().getTheme(), 0, 0, 10, 10, "Windmill");
        this.table.attachChild(this.windMillButton);
        this.windMillButton.addButtonListener(new IButtonListener() {
            @Override
            public void onClicked(IButton button) {
                animatedMeshedMenu.getSceneManager().showScene(new WindMillScene(animatedMeshedMenu.getSceneManager()));
            }
        });

        this.housesButton = new TextButton(this.getSceneManager().getTheme(), 0, 0, 10, 10, "Houses");
        this.table.attachChild(this.housesButton);
        this.housesButton.addButtonListener(new IButtonListener() {
            @Override
            public void onClicked(IButton button) {
                animatedMeshedMenu.getSceneManager().showScene(new HousesScene(animatedMeshedMenu.getSceneManager()));
            }
        });

        this.flowersButton = new TextButton(this.getSceneManager().getTheme(), 0, 0, 10, 10, "Flowers");
        this.table.attachChild(this.flowersButton);
        this.flowersButton.addButtonListener(new IButtonListener() {
            @Override
            public void onClicked(IButton button) {
                animatedMeshedMenu.getSceneManager().showScene(new FlowersScene(animatedMeshedMenu.getSceneManager()));
            }
        });


        this.forestButton = new TextButton(this.getSceneManager().getTheme(), 0, 0, 10, 10, "Forest");
        this.table.attachChild(this.forestButton);
        this.forestButton.addButtonListener(new IButtonListener() {
            @Override
            public void onClicked(IButton button) {
                animatedMeshedMenu.getSceneManager().showScene(new ForestScene(animatedMeshedMenu.getSceneManager()));
            }
        });



        this.attachChild(this.table);

    }


    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }
}
