package com.bitarcher.aeFun.interfaces.gui.theme;

import com.bitarcher.aeFun.interfaces.basicioc.INamed;
import com.bitarcher.aeFun.interfaces.basicioc.IService;
import com.bitarcher.aeFun.interfaces.gui.theme.widgetSections.IWidgetSections;

/**
 * Created by michel on 21/01/15.
 */
public interface ITheme extends INamed, IService{
    IThemeManager getThemeManager();

    // porcelain API
    IFontThemeSection getFontThemeSection();
    IColorsSection getColorsSection();


    // mid level API
    IWidgetSections getWidgetSections();


    // low level plumbing API
    ILayoutFactory getLayoutFactory();
}
