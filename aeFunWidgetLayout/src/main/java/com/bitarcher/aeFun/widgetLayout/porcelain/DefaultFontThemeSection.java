package com.bitarcher.aeFun.widgetLayout.porcelain;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import android.graphics.Typeface;

import com.bitarcher.aeFun.interfaces.gui.theme.EnumFontSize;
import com.bitarcher.aeFun.interfaces.gui.theme.IFontThemeSection;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.resourceManagement.ResourcesInfos.Font.FontCreateFromTypeFaceResourceInfo;

import org.andengine.opengl.font.Font;
import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 10/03/15.
 */
public class DefaultFontThemeSection implements IFontThemeSection {

    ITheme theme;
    protected FontCreateFromTypeFaceResourceInfo bigFontResourceInfo;
    protected FontCreateFromTypeFaceResourceInfo mediumFontResourceInfo;
    protected FontCreateFromTypeFaceResourceInfo smallFontResourceInfo;

    public DefaultFontThemeSection(ITheme theme)
    {
        this.theme = theme;

        int c = 1024;
        this.bigFontResourceInfo =new FontCreateFromTypeFaceResourceInfo("@default/bigFont", c, c, 36, this.getFontColor(EnumFontSize.Big).getARGBPackedInt(), true,
                Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        this.mediumFontResourceInfo =new FontCreateFromTypeFaceResourceInfo("@default/mediumFont", c, c, 32, this.getFontColor(EnumFontSize.Medium).getARGBPackedInt(), true,
                Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        this.smallFontResourceInfo =new FontCreateFromTypeFaceResourceInfo("@default/smallFont", c, c, 28, this.getFontColor(EnumFontSize.Small).getARGBPackedInt(), true,
                Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
    }

    @Override
    public Color getFontColor(EnumFontSize eFontSize) {

        Color retval = this.theme.getColorsSection().getFontColor();

        return retval;
    }

    protected FontCreateFromTypeFaceResourceInfo getBigFontResourceInfo()
    {
        return this.bigFontResourceInfo;
    }

    protected FontCreateFromTypeFaceResourceInfo getMediumFontResourceInfo()
    {
        return this.mediumFontResourceInfo;
    }

    protected FontCreateFromTypeFaceResourceInfo getSmallFontResourceInfo()
    {
        return this.smallFontResourceInfo;
    }


    protected Font getBigFont()  {
        return this.theme.getThemeManager().getResourceManager().getFont(this.getBigFontResourceInfo());
    }

    protected Font getMediumFont()  {
        return this.theme.getThemeManager().getResourceManager().getFont(this.getMediumFontResourceInfo());
    }

    protected Font getSmallFont()  {
        return this.theme.getThemeManager().getResourceManager().getFont(this.getSmallFontResourceInfo());
    }



    @Override
    public Font getFont(EnumFontSize eFontSize) {
        Font retval = null;

        switch (eFontSize)
        {
            case Big:
                retval = this.getBigFont();
                break;
            case Medium:
                retval = this.getMediumFont();
                break;
            case Small:
                retval = this.getSmallFont();
                break;
        }

        return retval;
    }

    @Override
    public void pushResourceRequirements() {
        this.theme.getThemeManager().getResourceManager().pushRequirement(this.bigFontResourceInfo);
        this.theme.getThemeManager().getResourceManager().pushRequirement(this.mediumFontResourceInfo);
        this.theme.getThemeManager().getResourceManager().pushRequirement(this.smallFontResourceInfo);
    }

    @Override
    public void popResourceRequirements() {
        this.theme.getThemeManager().getResourceManager().popRequirement(this.bigFontResourceInfo);
        this.theme.getThemeManager().getResourceManager().popRequirement(this.mediumFontResourceInfo);
        this.theme.getThemeManager().getResourceManager().popRequirement(this.smallFontResourceInfo);
    }
}
