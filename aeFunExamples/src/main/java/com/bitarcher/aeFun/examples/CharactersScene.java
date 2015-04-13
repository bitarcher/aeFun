package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.drawables.characters.animals.Dog;
import com.bitarcher.aeFun.geometry.primitives.CheckSymbol;
import com.bitarcher.aeFun.geometry.primitives.DiskOrXGon;
import com.bitarcher.aeFun.interfaces.drawables.characters.EnumMainPosition;
import com.bitarcher.aeFun.interfaces.drawables.characters.EnumSide;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;
import com.bitarcher.aeFun.widgetToolkit.widget.AnalogOnScreenControl;
import com.bitarcher.aeFun.widgetToolkit.widget.Image;
import com.bitarcher.aeFun.widgetToolkit.widget.Label;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
import org.andengine.util.math.MathConstants;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class CharactersScene extends ManagedGameScene implements org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener {

    BannerCtrl bannerCtrl;
    AnalogOnScreenControl analogOnScreenControl;
    Dog dog;
    Label infos;


	public CharactersScene(ITSceneManager sceneManager) {
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

        this.bannerCtrl = new BannerCtrl(theme, camera.getWidth() / 2,  camera.getHeight() * 5 / 6, camera.getWidth(), camera.getHeight() / 3, "Characters");
        this.attachChild(this.bannerCtrl); // two columns span

        this.analogOnScreenControl = new AnalogOnScreenControl(theme, 100, 100, 200, 200);
        this.analogOnScreenControl.addAnalogOnScreenControlListener(this);

        this.attachChild(this.analogOnScreenControl);

        this.dog = new Dog(theme.getThemeManager().getResourceManager());
        this.dog.setPosition(400, 200);
        this.dog.start();
        this.attachChild(this.dog);

        this.infos = new Label(theme, 500, 100, 600, 100, "");
        this.attachChild(this.infos);

    }

    @Override
    public void onUnloadScene() {
        this.mChildren.clear();
    }

    @Override
    public void onControlClick(org.andengine.engine.camera.hud.controls.AnalogOnScreenControl pAnalogOnScreenControl) {

    }

    @Override
    public void onControlChange(BaseOnScreenControl pBaseOnScreenControl, float pValueX, float pValueY) {

        EnumMainPosition mainPosition = EnumMainPosition.Idle;

        EnumSide side = EnumSide.Right;

        if (pValueX < 0) {
            side = EnumSide.Left;
        }


        if (pValueX != 0) {

            double strength = Math.sqrt(pValueX * pValueX + pValueY * pValueY);


            if (strength > 0.5) {
                mainPosition = EnumMainPosition.Run;
            } else {
                mainPosition = EnumMainPosition.Walk;
            }
            this.infos.setTranslatedLabel(String.format("x=%.2f, y=%.2f, le=%.2f, si=%s, mp=%s", pValueX, pValueY, strength, side.toString(), mainPosition.toString()));
        } else {

            this.infos.setTranslatedLabel("x = 0");
        }


        this.dog.setMainPosition(side, mainPosition);
    }

}
