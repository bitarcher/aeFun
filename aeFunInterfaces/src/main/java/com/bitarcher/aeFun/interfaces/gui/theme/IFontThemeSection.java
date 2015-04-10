package com.bitarcher.aeFun.interfaces.gui.theme;

import com.bitarcher.aeFun.interfaces.resourcemanagement.IResourceRequirementsStackUser;

import org.andengine.opengl.font.Font;
import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 22/01/15.
 */
public interface IFontThemeSection extends IResourceRequirementsStackUser {

    Font getFont(EnumFontSize eFontSize);
    Color getFontColor(EnumFontSize eFontSize);

}
