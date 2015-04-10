package com.bitarcher.aeFun.examples;



import com.bitarcher.aeFun.interfaces.gui.theme.IThemeManager;
import com.bitarcher.aeFun.interfaces.sceneManagement.ISceneManagerConfigurator;
import com.bitarcher.aeFun.interfaces.sceneManagement.ITSceneManager;
import com.bitarcher.aeFun.resourceManagement.ResourceManager;
import com.bitarcher.aeFun.sceneManagement.SceneManagedActivity;
import com.bitarcher.aeFun.widgetLayout.theme.DefaultTheme;



/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

public class MainActivity extends SceneManagedActivity<ResourceManager, DefaultTheme, MainMenu, OptionsLayer> {

    // ===========================================================
    // Constants
    // ===========================================================


    @Override
    protected ISceneManagerConfigurator<ResourceManager, DefaultTheme, MainMenu, OptionsLayer> getSceneManagerConfigurator() {
        return new ISceneManagerConfigurator<ResourceManager, DefaultTheme, MainMenu, OptionsLayer>() {
            @Override
            public ResourceManager getNewResourceManager() {
                return new ResourceManager();
            }

            @Override
            public DefaultTheme getNewTheme(IThemeManager themeManager) {
                return new DefaultTheme(themeManager, "@Default");
            }

            @Override
            public MainMenu getNewMainMenu(ITSceneManager<ResourceManager, DefaultTheme, MainMenu, OptionsLayer> sceneManager, DefaultTheme theme, ResourceManager resourceManager) {
                return new MainMenu(sceneManager);
            }

            @Override
            public OptionsLayer getNewOptionsLayer(ITSceneManager<ResourceManager, DefaultTheme, MainMenu, OptionsLayer> sceneManager, DefaultTheme theme, ResourceManager resourceManager, MainMenu mainMenu) {
                return new OptionsLayer(sceneManager);
            }
        };
    }

    // ===========================================================

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) {


        //this.themeManager = new ThemeManager(resourceManager);
        //resourceManager.setup(this.mEngine, this.getApplicationContext(), CAMERA_WIDTH, CAMERA_HEIGHT, 1f, 1f, this.themeManager);

        //SceneManager.getInstance().setup(resourceManager);

        //this.theme = new DefaultTheme(themeManager, "raw/theme/default");
        //themeManager.setCurrentTheme(theme);

        super.onCreateResources(pOnCreateResourcesCallback);


    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        super.onCreateScene(pOnCreateSceneCallback);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
