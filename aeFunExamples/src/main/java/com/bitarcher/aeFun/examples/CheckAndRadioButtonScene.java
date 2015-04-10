package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;

import com.bitarcher.aeFun.interfaces.gui.widgets.ICheckable;
import com.bitarcher.aeFun.interfaces.gui.widgets.ICheckableListener;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;

import com.bitarcher.aeFun.widgetToolkit.widget.CheckButton;
import com.bitarcher.aeFun.widgetToolkit.widget.Label;
import com.bitarcher.aeFun.widgetToolkit.widget.RadioButton;
import com.bitarcher.aeFun.widgetToolkit.widget.RadioButtonGroup;
import com.bitarcher.aeFun.widgetToolkit.widget.Table;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class CheckAndRadioButtonScene extends ManagedGameScene {
    Table table;
    BannerCtrl bannerCtrl;

    CheckButton checkButton1;
    CheckButton checkButton2;
    Label checkButton1Label;
    RadioButton radioButton1a;
    RadioButton radioButton1b;
    RadioButton radioButton1c;
    RadioButton radioButton2a;
    RadioButton radioButton2b;



	public CheckAndRadioButtonScene(ITSceneManager sceneManager) {
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
        this.table = new Table(theme, camera.getWidth() / 2,  camera.getHeight() / 2, camera.getWidth(), camera.getHeight());
        //this.table = new Table(theme, camera.getWidth() / 2,  camera.getHeight() / 3 * 2, camera.getWidth(), camera.getHeight() / 3 * 2);

        this.table.addHomogeneousColumnsAndRows(2, 5, 5);
        //this.table.addHomogeneousColumnsAndRows(2, 2, 5);

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() / 6, camera.getWidth(), camera.getHeight() / 3, "Check and radio buttons");
        this.table.attachChild(this.bannerCtrl, 0, 0, 2, 1); // two columns span

        this.checkButton1 = new CheckButton(theme, 0, 0, 10, 10);
        this.checkButton1.setTranslatedLabel("Check button 1");

        final CheckAndRadioButtonScene checkAndRadioButtonScene = this;

        this.checkButton1.addCheckableListener(new ICheckableListener() {

            @Override
            public void onCheckedChanged(ICheckable checkable, boolean isChecked) {
                checkAndRadioButtonScene.checkButton1Label.setTranslatedLabel(isChecked?"Button 1 checked":"Button 1 NOT checked");
            }
        });

        this.table.attachChild(this.checkButton1, 0, 1);

        this.checkButton1Label = new Label(theme, 0, 0, 10, 10, "Not checked");
        this.table.attachChild(this.checkButton1Label, 0, 2);

        this.checkButton2 = new CheckButton(theme, 0, 0, 10, 10);
        this.checkButton2.setTranslatedLabel("Check button 2");
        this.table.attachChild(this.checkButton2, 0, 3);



        RadioButtonGroup radioButtonGroup1 = new RadioButtonGroup();

        this.radioButton1a = new RadioButton(theme, 0, 0, 10, 10, radioButtonGroup1);
        this.radioButton1a.setTranslatedLabel("Radio button 1a");
        this.table.attachChild(this.radioButton1a, 1, 1);

        this.radioButton1b = new RadioButton(theme, 0, 0, 10, 10, radioButtonGroup1);
        this.radioButton1b.setTranslatedLabel("Radio button 1b");
        this.table.attachChild(this.radioButton1b, 1, 2);

        this.radioButton1c = new RadioButton(theme, 0, 0, 10, 10, radioButtonGroup1);
        this.radioButton1c.setTranslatedLabel("Radio button 1c");
        this.table.attachChild(this.radioButton1c, 1, 3);

        RadioButtonGroup radioButtonGroup2 = new RadioButtonGroup();

        this.radioButton2a = new RadioButton(theme, 0, 0, 10, 10, radioButtonGroup2);
        this.radioButton2a.setTranslatedLabel("Radio button 2a");
        this.table.attachChild(this.radioButton2a, 0, 4);

        this.radioButton2b = new RadioButton(theme, 0, 0, 10, 10, radioButtonGroup2);
        this.radioButton2b.setTranslatedLabel("Radio button 2b");
        this.table.attachChild(this.radioButton2b, 1, 4);



        this.attachChild(this.table);
    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }
}
