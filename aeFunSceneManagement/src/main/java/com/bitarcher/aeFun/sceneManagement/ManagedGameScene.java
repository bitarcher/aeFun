package com.bitarcher.aeFun.sceneManagement;

import com.bitarcher.aeFun.interfaces.gui.andEngine.IScene;
import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;

public abstract class ManagedGameScene extends ManagedScene {
	// Create an easy to manage HUD that we can attachChild/detachChild when the game scene is shown or hidden.
	public HUD GameHud = new HUD();
	public ManagedGameScene thisManagedGameScene = this;
	
	public ManagedGameScene(ITSceneManager sceneManager) {
		// Let the Scene Manager know that we want to show a Loading Scene for at least 2 seconds.
		this(sceneManager, 2f);
	};
	
	public ManagedGameScene(ITSceneManager sceneManager, float pLoadingScreenMinimumSecondsShown) {
		super(sceneManager, pLoadingScreenMinimumSecondsShown);
		// Setup the touch attributes for the Game Scenes.
		this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
		// Scale the Game Scenes according to the Camera's scale factor.
		//this.setScale(this.sceneManager.getResourceManager().getCameraScaleX(), this.sceneManager.getResourceManager().getCameraScaleY());
		//this.setPosition(0, this.sceneManager.getResourceManager().getCameraHeight()/2f);
		//GameHud.setScaleCenter(0f, 0f);
		//GameHud.setScale(this.sceneManager.getResourceManager().getCameraScaleX(), this.sceneManager.getResourceManager().getCameraScaleY());
	}
	
	// These objects will make up our loading scene.
	private Text loadingText;
	private LoadingScene loadingScene;
	@Override
	public IScene onLoadingScreenLoadAndShown() {
		// Setup and return the loading screen.
		loadingScene = new LoadingScene();
		loadingScene.setBackgroundEnabled(true);
		loadingText = new Text(0,0, this.sceneManager.getResourceManager().getThemeManager().getCurrentTheme().getFontThemeSection().getFont(EnumFontSize.Big),"Loading...", this.sceneManager.getResourceManager().getEngine().getVertexBufferObjectManager());
		loadingText.setPosition(loadingText.getWidth()/2f, this.sceneManager.getResourceManager().getCameraHeight()- loadingText.getHeight()/2f);
		loadingScene.attachChild(loadingText);
		return loadingScene;
	}

	@Override
	public void onLoadingScreenUnloadAndHidden() {
		// detachChild the loading screen resources.
		loadingText.detachSelf();
		loadingText = null;
		loadingScene = null;
	}
	
	@Override
	public void onLoadScene() {
		// Load the resources to be used in the Game Scenes.
		//OriginalOldResourceManager.loadGameResources();
		
		// Create a Sprite to use as the background.
		//this.attachChild(new Sprite(0,0, OriginalOldResourceManager.gameBackgroundTextureRegion, OriginalOldResourceManager.getInstance().engine.getVertexBufferObjectManager()));
		//this.getLastChild().setScaleCenter(0f,0f);
		//this.getLastChild().setScaleX(800f);
		
		// Setup the HUD Buttons and Button Texts.
		// Take note of what happens when the buttons are clicked.
		/*ButtonSprite MainMenuButton = new ButtonSprite(0f,0f,
				this.resourceManager..buttonTiledTextureRegion.getTextureRegion(0),
				OriginalOldResourceManager.buttonTiledTextureRegion.getTextureRegion(1),
				OriginalOldResourceManager.getInstance().engine.getVertexBufferObjectManager());
		MainMenuButton.setScale(1/ OriginalOldResourceManager.getInstance().cameraScaleFactorX, 1/ OriginalOldResourceManager.getInstance().cameraScaleFactorY);
		MainMenuButton.setPosition((MainMenuButton.getWidth()*MainMenuButton.getScaleX())/2f, (MainMenuButton.getHeight()*MainMenuButton.getScaleY())/2f);
		MainMenuButton.setOnClickListener(new ButtonSprite.OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// Play the click sound and show the Main Menu.
				OriginalOldResourceManager.clickSound.play();
				SceneManager.getInstance().showMainMenu();
			}});*/
		
		/*Text MainMenuButtonText = new Text(MainMenuButton.getWidth()/2,MainMenuButton.getHeight()/2, OriginalOldResourceManager.fontDefault32Bold,"MENU", OriginalOldResourceManager.getInstance().engine.getVertexBufferObjectManager());
		MainMenuButton.attachChild(MainMenuButtonText);
		GameHud.attachChild(MainMenuButton);
		GameHud.registerTouchArea(MainMenuButton);
		
		ButtonSprite OptionsButton = new ButtonSprite(0f,0f, 
				OriginalOldResourceManager.buttonTiledTextureRegion.getTextureRegion(0),
				OriginalOldResourceManager.buttonTiledTextureRegion.getTextureRegion(1),
				OriginalOldResourceManager.getInstance().engine.getVertexBufferObjectManager());
		OptionsButton.setScale(1/ OriginalOldResourceManager.getInstance().cameraScaleFactorX, 1/ OriginalOldResourceManager.getInstance().cameraScaleFactorY);
		OptionsButton.setPosition(800f-((OptionsButton.getWidth()*OptionsButton.getScaleX())/2f), (OptionsButton.getHeight()*OptionsButton.getScaleY())/2f);
		OptionsButton.setOnClickListener(new ButtonSprite.OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// Play the click sound and show the Options Layer.
				OriginalOldResourceManager.clickSound.play();
				SceneManager.getInstance().showOptionsLayer(true);
			}});
		
		Text OptionsButtonText = new Text(0,0, OriginalOldResourceManager.fontDefault32Bold,"OPTIONS", OriginalOldResourceManager.getInstance().engine.getVertexBufferObjectManager());
		OptionsButtonText.setPosition((OptionsButton.getWidth())/2, (OptionsButton.getHeight())/2);
		OptionsButton.attachChild(OptionsButtonText);
		GameHud.attachChild(OptionsButton);
		GameHud.registerTouchArea(OptionsButton);*/
	}


	
	@Override
	public void onShowScene() {
		// We want to wait to set the HUD until the scene is shown because otherwise it will appear on top of the loading screen.
		this.sceneManager.getResourceManager().getEngine().getCamera().setHUD(GameHud);
	}
	
	@Override
	public void onHideScene() {
        this.sceneManager.getResourceManager().getEngine().getCamera().setHUD(null);
	}
	
	@Override
	public void onUnloadScene() {
		// detachChild and unload the scene.
        this.sceneManager.getResourceManager().getEngine().runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
				thisManagedGameScene.detachChildren();
				thisManagedGameScene.clearEntityModifiers();
				thisManagedGameScene.clearTouchAreas();
				thisManagedGameScene.clearUpdateHandlers();
			}});
	}
}