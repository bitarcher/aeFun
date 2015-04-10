package com.bitarcher.aeFun.sceneManagement;


import android.content.Context;
import android.view.KeyEvent;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IContextProvider;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;
import com.bitarcher.aeFun.interfaces.sceneManagement.IMainMenu;
import com.bitarcher.aeFun.interfaces.sceneManagement.IOptionsLayer;
import com.bitarcher.aeFun.interfaces.sceneManagement.ISceneManagerConfigurator;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.ui.activity.BaseGameActivity;

public abstract class SceneManagedActivity<TResourceManager extends IResourceManager, TTheme extends ITheme, TMainMenu extends IMainMenu, TOptionsLayer extends IOptionsLayer> extends BaseGameActivity implements IContextProvider{

    SceneManager<TResourceManager, TTheme, TMainMenu, TOptionsLayer> sceneManager;



    public SceneManager<TResourceManager, TTheme, TMainMenu, TOptionsLayer> getSceneManager()
    {
        return this.sceneManager;
    }

	// ====================================================

    public SceneManagedActivity() {



    }

    protected abstract ISceneManagerConfigurator<TResourceManager, TTheme, TMainMenu, TOptionsLayer> getSceneManagerConfigurator();


    // CONSTANTS
	// ====================================================
	// We define these constants to setup the game to use an
	// appropriate camera resolution independent of the actual
	// end-user's screen resolution.
	
	// The resolution of the screen with which you are developing.
	static float DESIGN_SCREEN_WIDTH_PIXELS = 800f;
	static float DESIGN_SCREEN_HEIGHT_PIXELS = 480f;
	// The physical size of the screen with which you are developing.
	static float DESIGN_SCREEN_WIDTH_INCHES = 4.472441f;
	static float DESIGN_SCREEN_HEIGHT_INCHES = 2.805118f;
	// Define a minimum and maximum screen resolution (to prevent
	// cramped or overlapping screen elements).
	static float MIN_WIDTH_PIXELS = 320f, MIN_HEIGHT_PIXELS = 240f;
	static float MAX_WIDTH_PIXELS = 1600f, MAX_HEIGHT_PIXELS = 960f;
	
	// ====================================================
	// VARIABLES
	// ====================================================
	// These variables will be set in onCreateEngineOptions().
	//public float cameraWidth;
	//public float cameraHeight;
	//public float actualScreenWidthInches;
	//public float actualScreenHeightInches;
	
	// If a Layer is open when the Back button is pressed, hide the layer.
	// If a Game scene or non-MainMenu is active, go back to the MainMenu.
	// Otherwise, exit the game.
	@Override
	public boolean onKeyDown(final int keyCode, final KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if(this.sceneManager.resourceManager.getEngine()!=null){
				if(this.sceneManager.isLayerShown())
                    this.sceneManager.getCurrentLayer().onHideLayer();
				else if(this.sceneManager.getCurrentScene().getClass().getGenericSuperclass().equals(ManagedGameScene.class) ||
						(this.sceneManager.getCurrentScene().getClass().getGenericSuperclass().equals(ManagedMenuScene.class) &!
                                (this.sceneManager.getCurrentScene() instanceof IMainMenu)))
                    this.sceneManager.showMainMenu();
				else
					System.exit(0);
			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

    protected float getComputedDynamicOptimalCameraWidth()
    {
        float actualScreenWidthInches = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().xdpi;

        float retval =  Math.round(Math.max(Math.min(DESIGN_SCREEN_WIDTH_PIXELS * (actualScreenWidthInches / DESIGN_SCREEN_WIDTH_INCHES),MAX_WIDTH_PIXELS),MIN_WIDTH_PIXELS));

        return retval;
    }

    protected float getComputedDynamicOptimalCameraHeight()
    {
        float actualScreenHeightInches = getResources().getDisplayMetrics().heightPixels / getResources().getDisplayMetrics().ydpi;
        float retval = Math.round(Math.max(Math.min(DESIGN_SCREEN_HEIGHT_PIXELS * (actualScreenHeightInches / DESIGN_SCREEN_HEIGHT_INCHES),MAX_HEIGHT_PIXELS),MIN_HEIGHT_PIXELS));

        return retval;
    }

    protected IResolutionPolicy getResolutionPolicy()
    {
        return new RatioResolutionPolicy(800, 480);
    }

    protected int getWantedCameraWidth()
    {
        return 800;
    }

    protected int getWantedCameraHeight()
    {
        return 480;
    }

	// ====================================================
	// CREATE ENGINE OPTIONS
	// ====================================================
	@Override
	public EngineOptions onCreateEngineOptions() {
		// Create the EngineOptions.
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, this.getResolutionPolicy(),
                new Camera(0, 0, this.getWantedCameraWidth(), this.getWantedCameraHeight()));
		// Enable sounds.
		engineOptions.getAudioOptions().setNeedsSound(true);

        // added 20150226, seems to be required for scrolling menu
        engineOptions.getTouchOptions().setNeedsMultiTouch(true);

		// Enable music.
		engineOptions.getAudioOptions().setNeedsMusic(true);
		// Turn on Dithering to smooth texture gradients.
		engineOptions.getRenderOptions().setDithering(true);
		// Turn on MultiSampling to smooth the alias of hard-edge elements.
		engineOptions.getRenderOptions().getConfigChooserOptions().setRequestedMultiSampling(true);
		// Set the Wake Lock options to prevent the engine from dumping textures when focus changes.
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engineOptions;
	}
	
	// ====================================================
	// CREATE RESOURCES
	// ====================================================
	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) {
		// Setup the ResourceManager.
		//OriginalOldResourceManager.getInstance().setup(this.getEngine(), this.getApplicationContext(), cameraWidth, cameraHeight, cameraWidth/DESIGN_SCREEN_WIDTH_PIXELS, cameraHeight/DESIGN_SCREEN_HEIGHT_PIXELS);

        ISceneManagerConfigurator<TResourceManager, TTheme, TMainMenu, TOptionsLayer> sceneManagerConfigurator = this.getSceneManagerConfigurator();
        Engine engine = this.getEngine();
        this.sceneManager = new SceneManager<>(sceneManagerConfigurator, engine, this);

		pOnCreateResourcesCallback.onCreateResourcesFinished();

	}

	// ====================================================
	// CREATE SCENE
	// ====================================================
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
		// Register an FPSLogger to output the game's FPS during development.
		mEngine.registerUpdateHandler(new FPSLogger());
		// Tell the SceneManager to show the MainMenu.
		//SceneManager.getInstance().showMainMenu();
        this.sceneManager.showMainMenu();
		// Set the MainMenu to the Engine's scene.

        pOnCreateSceneCallback.onCreateSceneFinished((Scene)this.getSceneManager().getMainMenu());
	}

	// ====================================================
	// POPULATE SCENE
	// ====================================================
	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) {
		// Our SceneManager will handle the population of the scenes, so we do nothing here.
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }
}