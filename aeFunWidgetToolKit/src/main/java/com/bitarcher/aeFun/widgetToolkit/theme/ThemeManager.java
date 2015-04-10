package com.bitarcher.aeFun.widgetToolkit.theme;

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.IThemeManager;
import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by michel on 22/01/15.
 */
public class ThemeManager implements IThemeManager {

    @Nullable
    ITheme currentTheme;

    @NotNull
    IResourceManager resourceManager;

    public ThemeManager(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    public void setCurrentTheme(ITheme currentTheme) {

        if(this.currentTheme != null)
        {
            this.currentTheme.stop();
        }

        this.currentTheme = currentTheme;

        if(this.currentTheme != null)
        {
            this.currentTheme.start();
        }
    }

    @Override
    public ITheme getCurrentTheme() {

        return currentTheme;
    }

    @Override
    public IResourceManager getResourceManager() {
        return resourceManager;
    }
}
