package com.bitarcher.aeFun.widgetLayout.theme;

import com.bitarcher.aeFun.interfaces.gui.theme.IColorsSection;
import com.bitarcher.aeFun.interfaces.gui.theme.IFontThemeSection;
import com.bitarcher.aeFun.interfaces.gui.theme.ILayoutFactory;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.IThemeManager;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IWidgetSections;
import com.bitarcher.aeFun.interfaces.resourcemanagement.EResourceNotFound;

import java.util.Date;

/**
 * Created by michel on 21/01/15.
 */
public abstract class ThemeBase implements ITheme {
    IThemeManager themeManager;
    String name;

    ILayoutFactory layoutFactory;
    IFontThemeSection fontThemeSection;
    IWidgetSections widgetSections;
    IColorsSection colorsSection;


    protected abstract ILayoutFactory getNewLayoutFactory();
    protected abstract IWidgetSections getNewWidgetSections();
    protected abstract IFontThemeSection getNewFontThemeSection();
    protected abstract IColorsSection getNewColorsSection();

    @Override
    public  IFontThemeSection getFontThemeSection()
    {
        if(this.fontThemeSection == null)
        {
            this.fontThemeSection = this.getNewFontThemeSection();
        }

        return this.fontThemeSection;
    }

    @Override
    public IWidgetSections getWidgetSections()
    {
        if(this.widgetSections == null)
        {
            this.widgetSections = this.getNewWidgetSections();
        }

        return this.widgetSections;
    }


    @Override
    public  ILayoutFactory getLayoutFactory()
    {
        if(this.layoutFactory == null)
        {
            this.layoutFactory = this.getNewLayoutFactory();
        }

        return this.layoutFactory;
    }

    @Override
    public IColorsSection getColorsSection() {
        if(this.colorsSection == null)
        {
            this.colorsSection = this.getNewColorsSection();
        }

        return this.colorsSection;
    }


    @Override
    public IThemeManager getThemeManager() {
        return this.themeManager;
    }

    protected ThemeBase(IThemeManager themeManager, String name) {
        this.themeManager = themeManager;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }


    protected void onPushFontRequirements()
    {
        this.getFontThemeSection().pushResourceRequirements();
    }

    protected void onPopFontRequirements()
    {
        this.getFontThemeSection().popResourceRequirements();
    }

    protected void onLoadOtherResources()
    {

    }

    protected void onUnloadOtherResources()
    {

    }

    @Override
    public final void start() {
        this.onPushFontRequirements();
        this.onLoadOtherResources();
    }

    @Override
    public final void stop() {
        this.onUnloadOtherResources();

        try {
            this.onPopFontRequirements();
        } catch (EResourceNotFound eResourceNotFound) {
            eResourceNotFound.printStackTrace();
        }
    }

    @Override
    public void tic(Date dateTime) {
        // not used
    }
}
