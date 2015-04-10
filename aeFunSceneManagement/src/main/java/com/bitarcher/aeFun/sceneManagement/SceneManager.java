package com.bitarcher.aeFun.sceneManagement;


import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.IThemeManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IContextProvider;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.sceneManagement.IMainMenu;
import com.bitarcher.aeFun.interfaces.sceneManagement.IManagedLayer;
import com.bitarcher.aeFun.interfaces.sceneManagement.IManagedScene;
import com.bitarcher.aeFun.interfaces.sceneManagement.IOptionsLayer;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.interfaces.sceneManagement.ISceneManagerConfigurator;
import com.bitarcher.aeFun.widgetToolkit.theme.ThemeManager;


import org.andengine.engine.Engine;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;


public class SceneManager<TResourceManager extends IResourceManager, TTheme extends ITheme, TMainMenu extends IMainMenu,
        TOptionsLayer extends IOptionsLayer> implements ITSceneManager<TResourceManager, TTheme, TMainMenu, TOptionsLayer>
{
    TResourceManager resourceManager;


    TTheme theme;
    IThemeManager themeManager;
    TMainMenu mainMenu;
    TOptionsLayer optionsLayer;

    // ====================================================
    // VARIABLES
    // ====================================================
    // These variables reference the current scene and next scene when switching scenes.
    private IManagedScene mCurrentScene;
    private IManagedScene mNextScene;
    // Keep a reference to the engine.




    // Used by the mLoadingScreenHandler, this variable ensures that the loading screen is shown for one frame prior to loading resources.
    private int mNumFramesPassed = -1;
    // Keeps the mLoadingScreenHandler from being registered with the engine if it has already been registered.
    private boolean mLoadingScreenHandlerRegistered = false;
    // An update handler that shows the loading screen of mNextScene before calling it to load.
    private IUpdateHandler mLoadingScreenHandler;
    // Set to TRUE in the showLayer() method if the camera had a HUD before the layer was shown.
    private boolean mCameraHadHud = false;
    // Boolean to reflect whether there is a layer currently shown on the screen.
    private boolean isLayerShown = false;
    // An empty place-holder scene that we use to apply the modal properties of the layer to the currently shown scene.
    private Scene mPlaceholderModalScene;
    // Hold a reference to the current managed layer (if one exists).
    private IManagedLayer currentLayer;



    public SceneManager(ISceneManagerConfigurator<TResourceManager, TTheme, TMainMenu, TOptionsLayer> sceneManagerConfigurator, Engine engine, IContextProvider contextProvider) {
        this.resourceManager = sceneManagerConfigurator.getNewResourceManager();

        this.themeManager = new ThemeManager(this.resourceManager);
        this.resourceManager.setup(engine, contextProvider, this.themeManager);

        this.theme = sceneManagerConfigurator.getNewTheme(this.themeManager);
        this.themeManager.setCurrentTheme(theme);


        this.mainMenu = sceneManagerConfigurator.getNewMainMenu(this, this.theme, this.resourceManager);
        this.optionsLayer = sceneManagerConfigurator.getNewOptionsLayer(this, this.theme, this.resourceManager, this.mainMenu);

        this.mLoadingScreenHandler = new IUpdateHandler() {
            @Override
            public void onUpdate(float pSecondsElapsed) {
                // Increment the mNumFamesPassed
                mNumFramesPassed++;
                // And increment the amount of time that the loading screen has been visible.
                mNextScene.setElapsedLoadingScreenTime(mNextScene.getElapsedLoadingScreenTime() + pSecondsElapsed);
                // On the first frame AFTER the loading screen has been shown.
                if(mNumFramesPassed==1) {
                    // Hide and unload the previous scene if it exists.
                    if(getCurrentScene() !=null) {
                        getCurrentScene().onHideManagedScene();
                        getCurrentScene().onUnloadManagedScene();
                    }
                    // Load the new scene.
                    mNextScene.onLoadManagedScene();
                }
                // On the first frame AFTER the scene has been completely loaded and the loading screen has been shown for its minimum limit.
                if(mNumFramesPassed>1 && mNextScene.getElapsedLoadingScreenTime() >= mNextScene.getMinLoadingScreenTime()) {
                    // Remove the loading screen that was set as a child in the showScene() method.
                    mNextScene.clearChildScene();
                    // Tell the new scene to unload its loading screen.
                    mNextScene.onLoadingScreenUnloadAndHidden();
                    // Tell the new scene that it is shown.
                    mNextScene.onShowManagedScene();
                    // Set the new scene to the current scene.
                    setCurrentScene(mNextScene);
                    // Reset the handler & loading screen variables to be ready for another use.
                    mNextScene.setElapsedLoadingScreenTime(0f);
                    mNumFramesPassed = -1;
                    resourceManager.getEngine().unregisterUpdateHandler(this);
                    mLoadingScreenHandlerRegistered = false;
                }
            }
            @Override public void reset() {}
        };
    }

    @Override
    public TMainMenu getMainMenu() {
        return this.mainMenu;
    }

    @Override
    public TOptionsLayer getOptionsLayer() {
        return this.optionsLayer;
    }

    @Override
    public TResourceManager getResourceManager()
    {
        return this.resourceManager;
    }

    @Override
    public TTheme getTheme()
    {
        return this.theme;
    }

    @Override
    public IThemeManager getThemeManager()
    {
        return this.themeManager;
    }

    // ====================================================
	// PUBLIC METHODS
	// ====================================================
	// Initiates the process of switching the current managed scene for a new managed scene.
	public void showScene(final IManagedScene pManagedScene) {
		// Reset the camera. This is automatically overridden by any calls to alter the camera from within a managed scene's onShowScene() method.
        this.resourceManager.getEngine().getCamera().set(0f, 0f, this.resourceManager.getCameraWidth(), this.resourceManager.getCameraHeight());
		// If the new managed scene has a loading screen.
		if(pManagedScene.getHasLoadingScreen()) {
			// Set the loading screen as a modal child to the new managed scene.
			pManagedScene.setChildScene(pManagedScene.onLoadingScreenLoadAndShown(),true,true,true);
			// This if/else block assures that the LoadingScreen Update Handler is only registered if necessary.
			if(mLoadingScreenHandlerRegistered){
				mNumFramesPassed = -1;
				mNextScene.clearChildScene();
				mNextScene.onLoadingScreenUnloadAndHidden();
			} else {
                this.resourceManager.getEngine().registerUpdateHandler(mLoadingScreenHandler);
				mLoadingScreenHandlerRegistered = true;
			}
			// Set pManagedScene to mNextScene which is used by the loading screen update handler.
			mNextScene = pManagedScene;
			// Set the new scene as the engine's scene.
            this.resourceManager.getEngine().setScene((Scene)pManagedScene);
			// Exit the method and let the LoadingScreen Update Handler finish the switching.
			return;
		}
		// If the new managed scene does not have a loading screen.
		// Set pManagedScene to mNextScene and apply the new scene to the engine.
		mNextScene = pManagedScene;
        this.resourceManager.getEngine().setScene((org.andengine.entity.scene.Scene)mNextScene);
		// If a previous managed scene exists, hide and unload it.
		if(getCurrentScene() !=null)
		{
			getCurrentScene().onHideManagedScene();
			getCurrentScene().onUnloadManagedScene();
		}
		// Load and show the new managed scene, and set it as the current scene.
		mNextScene.onLoadManagedScene();
		mNextScene.onShowManagedScene();
		setCurrentScene(mNextScene);
	}
	
	// Convenience method to quickly show the Main Menu.
    @Override
	public void showMainMenu() {

		showScene(this.mainMenu);
	}

	// Convenience method to quickly show the Options Layer.
    @Override
	public void showOptionsLayer(final boolean pSuspendCurrentSceneUpdates) {

		showLayer(this.optionsLayer,false,pSuspendCurrentSceneUpdates,true);
	}

	// Shows a layer by placing it as a child to the Camera's HUD.
    @Override
	public void showLayer(final IManagedLayer pLayer, final boolean pSuspendSceneDrawing, final boolean pSuspendSceneUpdates, final boolean pSuspendSceneTouchEvents) {

        if(!(pLayer instanceof ManagedLayer))
            throw new IncompatibleClassChangeError();

		// If the camera already has a HUD, we will use it.
		if(this.resourceManager.getEngine().getCamera().hasHUD()){
			mCameraHadHud = true;
		} else {
			// Otherwise, we will create one to use.
			mCameraHadHud = false;
			HUD placeholderHud = new HUD();
            this.resourceManager.getEngine().getCamera().setHUD(placeholderHud);
		}
		// If the managed layer needs modal properties, set them.
		if(pSuspendSceneDrawing || pSuspendSceneUpdates || pSuspendSceneTouchEvents) {
			// Apply the managed layer directly to the Camera's HUD
            this.resourceManager.getEngine().getCamera().getHUD().setChildScene((ManagedLayer)pLayer, pSuspendSceneDrawing, pSuspendSceneUpdates, pSuspendSceneTouchEvents);
			// Create the place-holder scene if it needs to be created.
			if(mPlaceholderModalScene==null) {
				mPlaceholderModalScene = new Scene();
				mPlaceholderModalScene.setBackgroundEnabled(false);
			}
			// Apply the place-holder to the current scene.
			getCurrentScene().setChildScene(mPlaceholderModalScene, pSuspendSceneDrawing, pSuspendSceneUpdates, pSuspendSceneTouchEvents);
		} else {
			// If the managed layer does not need to be modal, simply set it to the HUD.
            this.resourceManager.getEngine().getCamera().getHUD().setChildScene((ManagedLayer)pLayer);
		}
		// Set the camera for the managed layer so that it binds to the camera if the camera is moved/scaled/rotated.
		pLayer.setCamera(this.resourceManager.getEngine().getCamera());
		// Scale the layer according to screen size.
		// pLayer.setScale(this.resourceManager.getCameraScaleX(), this.resourceManager.getCameraScaleY());
		// Let the layer know that it is being shown.
		pLayer.onShowManagedLayer();
		// Reflect that a layer is shown.
		setLayerShown(true);
		// Set the current layer to pLayer.
		setCurrentLayer(pLayer);
	}

	// Hides the open layer if one is open.
    @Override
	public void hideLayer() {
		if(isLayerShown()) {
			// Clear the HUD's child scene to remove modal properties.
            this.resourceManager.getEngine().getCamera().getHUD().clearChildScene();
			// If we had to use a place-holder scene, clear it.

            IManagedScene currentScene = getCurrentScene();
			if(currentScene.hasChildScene())
				if(currentScene.getIChildScene()==mPlaceholderModalScene)
                    currentScene.clearChildScene();
			// If the camera did not have a HUD before we showed the layer, remove the place-holder HUD.
			if(!mCameraHadHud)
                this.resourceManager.getEngine().getCamera().setHUD(null);
			// Reflect that a layer is no longer shown.
			setLayerShown(false);
			// Remove the reference to the layer.
			setCurrentLayer(null);
		}
	}

    @Override
    public IManagedScene getCurrentScene() {
        return mCurrentScene;
    }

    @Override
    public void setCurrentScene(IManagedScene mCurrentScene) {
        this.mCurrentScene = mCurrentScene;
    }

    @Override
    public boolean isLayerShown() {
        return isLayerShown;
    }

    @Override
    public void setLayerShown(boolean isLayerShown) {
        this.isLayerShown = isLayerShown;
    }

    @Override
    public IManagedLayer getCurrentLayer() {
        return currentLayer;
    }

    @Override
    public void setCurrentLayer(IManagedLayer currentLayer) {
        this.currentLayer = currentLayer;
    }
}