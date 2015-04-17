package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.drawables.characters.animals.Dog;

import com.bitarcher.aeFun.interfaces.drawables.characters.EnumMainPosition;
import com.bitarcher.aeFun.interfaces.geometry.EnumSide;
import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;

import com.bitarcher.aeFun.sceneManagement.ManagedGameScene;
import com.bitarcher.aeFun.widgetToolkit.widget.AnalogOnScreenControl;

import com.bitarcher.aeFun.widgetToolkit.widget.Label;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.primitive.Gradient;
import org.andengine.entity.scene.background.Background;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class CharactersScene extends ManagedGameScene implements org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener {

    final float maxDogX = 700;
    final float minDogX = 300;
    BannerCtrl bannerCtrl;
    AnalogOnScreenControl analogOnScreenControl;
    Dog dog;
    Label infos;
    EnumSide lastlyUsedSide = EnumSide.Right;
    Gradient groundGradient;
    BuildableBitmapTextureAtlas buildableBitmapTextureAtlas;
    ITextureRegion bushTexture;
    Sprite bushSprite;


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


        this.analogOnScreenControl = new AnalogOnScreenControl(theme, 100, 100, 200, 200);
        this.analogOnScreenControl.addAnalogOnScreenControlListener(this);

        this.attachChild(this.analogOnScreenControl);

        ArrayList<EnumMainPosition> mainPositions = new ArrayList<>();
        mainPositions.add(EnumMainPosition.Run);
        mainPositions.add(EnumMainPosition.Idle);
        mainPositions.add(EnumMainPosition.Walk);

        float dogY = 200;
        float dogX = (this.minDogX + this.maxDogX) / 2;
        float dogWidth = 90;
        float dogHeight = 90;
        float dogZoneWidth = this.maxDogX - this.minDogX;
        float dogZoneWidth2 = dogZoneWidth + dogWidth;

        this.buildableBitmapTextureAtlas =  new BuildableBitmapTextureAtlas(this.getSceneManager().getResourceManager().getEngine().getTextureManager(), 512, 512);

        this.bushTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buildableBitmapTextureAtlas,
                this.getSceneManager().getResourceManager().getContext(), "character/bush.png");

        try {
            this.buildableBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 1));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        buildableBitmapTextureAtlas.load();

        this.groundGradient = new Gradient(dogX, dogY, dogZoneWidth2, 150, this.getSceneManager().getResourceManager().getEngine().getVertexBufferObjectManager());
        this.groundGradient.setToColor(new Color(168f/255f, 96f/255f, 33f/255f));
        this.groundGradient.setFromColor(new Color(124f/255f, 85f/255f, 51f/255f));
        this.attachChild(this.groundGradient);

        this.bushSprite = new Sprite(dogX, dogY - 20, dogZoneWidth2, 100, this.bushTexture, this.getSceneManager().getResourceManager().getEngine().getVertexBufferObjectManager());


        this.attachChild(this.bushSprite);

        this.dog = new Dog(theme.getThemeManager().getResourceManager(), mainPositions);
        this.dog.setPosition(400, 200);
        this.dog.setSize(dogWidth, dogHeight);
        this.dog.start();
        this.attachChild(this.dog);

        this.infos = new Label(theme, 500, 100, 600, 100, "");
        this.attachChild(this.infos);


        this.attachChild(this.bannerCtrl); // two columns span
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

        EnumSide side;
        double strength = Math.sqrt(pValueX * pValueX + pValueY * pValueY);
        double usedStrength = strength * 5;

        if (pValueX < 0) {
            side = EnumSide.Left;

            if(this.dog.getX() > this.minDogX)
            {
                this.dog.setX(this.dog.getX() + (float)(pValueX * usedStrength));
            }
        }
        else if (pValueX == 0)
        {
            side = this.lastlyUsedSide;
        }
        else
        {
            side = EnumSide.Right;

            if(this.dog.getX() < this.maxDogX)
            {
                this.dog.setX(this.dog.getX() + (float)(pValueX * usedStrength));
            }
        }




        if (pValueX != 0) {

            if(strength > 0.5f) {

                mainPosition = EnumMainPosition.Run;
            }
            else
            {
                mainPosition = EnumMainPosition.Walk;
            }

            this.infos.setTranslatedLabel(String.format("x=%.2f, y=%.2f, le=%.2f, si=%s, mp=%s", pValueX, pValueY, strength, side.toString(), mainPosition.toString()));
        } else {


            this.infos.setTranslatedLabel("x = 0");
        }

        this.dog.setMainPosition(side, mainPosition);
        this.lastlyUsedSide = side;
    }

}

