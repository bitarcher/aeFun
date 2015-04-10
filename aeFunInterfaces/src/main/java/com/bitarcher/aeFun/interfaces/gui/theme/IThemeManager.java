package com.bitarcher.aeFun.interfaces.gui.theme;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceManagerLinked;

/**
 * Created by michel on 21/01/15.
 */
public interface IThemeManager extends IResourceManagerLinked {
    ITheme getCurrentTheme();
    void setCurrentTheme(ITheme theme);
}
