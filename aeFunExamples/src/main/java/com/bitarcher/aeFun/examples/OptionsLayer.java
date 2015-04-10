package com.bitarcher.aeFun.examples;

import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.sceneManagement.IOptionsLayer;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.resourceManagement.ResourceManager;
import com.bitarcher.aeFun.sceneManagement.ManagedLayer;
import com.bitarcher.aeFun.widgetLayout.theme.DefaultTheme;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class OptionsLayer extends ManagedLayer implements IOptionsLayer
{
    ITSceneManager<ResourceManager, DefaultTheme, MainMenu, OptionsLayer>  sceneManager;

    public OptionsLayer(final ITSceneManager<ResourceManager, DefaultTheme, MainMenu, OptionsLayer> sceneManager) {
        this.sceneManager = sceneManager;
        final OptionsLayer optionsLayer = this;

        this.SlideIn = new IUpdateHandler() {
            @Override
            public void onUpdate(float pSecondsElapsed) {
                if(optionsLayer.getY()> sceneManager.getResourceManager().getCameraHeight() / 2f) {
                    optionsLayer.setPosition(optionsLayer.getX(), Math.max(optionsLayer.getY() - (3600 * (pSecondsElapsed)), sceneManager.getResourceManager().getCameraHeight() / 2f));
                } else {
                    optionsLayer.unregisterUpdateHandler(this);
                }
            }
            @Override public void reset() {}
        };

        this.SlideOut = new IUpdateHandler() {
            @Override
            public void onUpdate(float pSecondsElapsed) {
                if(optionsLayer.getY()< sceneManager.getResourceManager().getCameraHeight() / 2f+480f) {
                    optionsLayer.setPosition(optionsLayer.getX(), Math.min(optionsLayer.getY() + (3600 * (pSecondsElapsed)), sceneManager.getResourceManager().getCameraHeight() / 2f + 480f));
                } else {
                    optionsLayer.unregisterUpdateHandler(this);
                    sceneManager.hideLayer();
                }
            }
            @Override public void reset() {}
        };
    }

    // Animates the layer to slide in from the top.
	IUpdateHandler SlideIn;
	
	// Animates the layer to slide out through the top and tell the SceneManager to hide it when it is off-screen;
	IUpdateHandler SlideOut;
	
	@Override
	public void onLoadLayer() {
		// Create and attachChild a background that hides the Layer when touched.
		final float BackgroundX = 0f, BackgroundY = 0f;
		final float BackgroundWidth = 760f, BackgroundHeight = 440f;
		Rectangle smth = new Rectangle(BackgroundX,BackgroundY,BackgroundWidth,BackgroundHeight, sceneManager.getResourceManager().getEngine().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp() && pTouchAreaLocalX < this.getWidth() && pTouchAreaLocalX > 0 && pTouchAreaLocalY < this.getHeight() && pTouchAreaLocalY > 0) {
                    // TODO
                    //sceneManager.getResourceManager().clickSound.play();
					onHideLayer();
				}
				return true;
			}
		};
		smth.setColor(0f, 0f, 0f, 0.85f);
		this.attachChild(smth);
		this.registerTouchArea(smth);
		
		// Create the OptionsLayerTitle text for the Layer.
		Text OptionsLayerTitle = new Text(0,0, sceneManager.getTheme().getFontThemeSection().getFont(EnumFontSize.Big),"OPTIONS", sceneManager.getResourceManager().getEngine().getVertexBufferObjectManager());
		OptionsLayerTitle.setPosition(0f,BackgroundHeight/2f-OptionsLayerTitle.getHeight());
		this.attachChild(OptionsLayerTitle);
		
		// Let the player know how to get out of the blank Options Layer
		Text OptionsLayerSubTitle = new Text(0,0, sceneManager.getTheme().getFontThemeSection().getFont(EnumFontSize.Medium),"Tap to return", sceneManager.getResourceManager().getEngine().getVertexBufferObjectManager());
		OptionsLayerSubTitle.setScale(0.75f);
		OptionsLayerSubTitle.setPosition(0f,-BackgroundHeight/2f+OptionsLayerSubTitle.getHeight());
		this.attachChild(OptionsLayerSubTitle);
		
		this.setPosition(sceneManager.getResourceManager().getCameraWidth()/2f, sceneManager.getResourceManager().getCameraHeight()/2f+480f);
	}

	@Override
	public void onShowLayer() {
		this.registerUpdateHandler(SlideIn);
	}

	@Override
	public void onHideLayer() {
		this.registerUpdateHandler(SlideOut);
	}
	@Override
	public void onUnloadLayer() {
	}
}