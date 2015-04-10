package com.bitarcher.aeFun.widgetLayout.theme;

import com.bitarcher.aeFun.interfaces.gui.theme.IColorsSection;
import com.bitarcher.aeFun.interfaces.gui.theme.IFontThemeSection;
import com.bitarcher.aeFun.interfaces.gui.theme.ILayoutFactory;
import com.bitarcher.aeFun.interfaces.gui.theme.IThemeManager;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IWidgetSections;

import com.bitarcher.aeFun.widgetLayout.DefaultLayoutFactory;

import com.bitarcher.aeFun.widgetLayout.porcelain.DefaultColorsSection;
import com.bitarcher.aeFun.widgetLayout.porcelain.DefaultFontThemeSection;
import com.bitarcher.aeFun.widgetLayout.porcelain.DefaultWidgetSections;


/**
 * Created by michel on 21/01/15.
 */
public class DefaultTheme extends ThemeBase {



    public DefaultTheme(IThemeManager themeManager, String name) {
        super(themeManager, name);


    }

    @Override
    protected ILayoutFactory getNewLayoutFactory() {
        return new DefaultLayoutFactory();
    }

    @Override
    protected IWidgetSections getNewWidgetSections() {
        return new DefaultWidgetSections(this);
    }

    @Override
    protected IFontThemeSection getNewFontThemeSection() {
        return new DefaultFontThemeSection(this);
    }

    @Override
    protected IColorsSection getNewColorsSection() {
        return new DefaultColorsSection(this);
    }
}

