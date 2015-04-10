package com.bitarcher.aeFun.examples;


import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.widgets.IButton;
import com.bitarcher.aeFun.interfaces.gui.widgets.IButtonListener;
import com.bitarcher.aeFun.interfaces.sceneManagement.IMainMenu;
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

public class MainMenu extends ManagedMenuScene implements IMainMenu{
    Table table;
    BannerCtrl bannerCtrl;
    TextButton widgetGalleryTextButton;
    TextButton resourceManagerTextButton;
    TextButton primitivesTextButton;
    TextButton characterTextButton;



	public MainMenu(ITSceneManager sceneManager) {
        super(sceneManager);
		/*this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);*/



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

        final MainMenu mainMenu = this;

        final ITSceneManager sceneManager = mainMenu.getSceneManager();
        this.setBackgroundEnabled(true);
        this.setBackground(new Background(1, 1, 1));

        ITheme theme = this.getSceneManager().getTheme();
        Camera camera = theme.getThemeManager().getResourceManager().getEngine().getCamera();
        this.table = new Table(theme, camera.getWidth() / 2,  camera.getHeight() / 2, camera.getWidth(), camera.getHeight());
        //this.table = new Table(theme, camera.getWidth() / 2,  camera.getHeight() / 3 * 2, camera.getWidth(), camera.getHeight() / 3 * 2);

        this.table.addHomogeneousColumnsAndRows(2, 3, 5);
        //this.table.addHomogeneousColumnsAndRows(2, 2, 5);

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() / 6, camera.getWidth(), camera.getHeight() / 3, "Top menu");
        this.table.attachChild(this.bannerCtrl, 0, 0, 2, 1); // two columns span

        this.widgetGalleryTextButton = new TextButton(this.getSceneManager().getTheme(), 0, 0, 10, 10, "Widget gallery");
        this.table.attachChild(this.widgetGalleryTextButton);
        this.widgetGalleryTextButton.addButtonListener(new IButtonListener() {
            @Override
            public void onClicked(IButton button) {
                WidgetGalleryMenu widgetGalleryMenu = new WidgetGalleryMenu(sceneManager);

                sceneManager.showScene(widgetGalleryMenu);
            }
        });

        this.resourceManagerTextButton = new TextButton(this.getSceneManager().getTheme(), 0, 0, 10, 10, "Resource manager");
        this.table.attachChild(this.resourceManagerTextButton);

        this.primitivesTextButton = new TextButton(this.getSceneManager().getTheme(), 0, 0, 10, 10, "Primitives");
        this.table.attachChild(this.primitivesTextButton);
        this.primitivesTextButton.addButtonListener(new IButtonListener() {
            @Override
            public void onClicked(IButton button) {
                PrimitivesScene primitivesScene = new PrimitivesScene(sceneManager);

                sceneManager.showScene(primitivesScene);
            }
        });

        this.attachChild(this.table);
    }


    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }
}
