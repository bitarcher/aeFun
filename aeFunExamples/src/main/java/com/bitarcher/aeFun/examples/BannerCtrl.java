package com.bitarcher.aeFun.examples;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.geometry.EnumAlignStyle;
import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.widgetToolkit.widget.Image;
import com.bitarcher.aeFun.widgetToolkit.widget.Label;
import com.bitarcher.aeFun.widgetToolkit.widget.Table;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.PercentSpaceUsage;

/**
 * Created by michel on 23/03/15.
 */
public class BannerCtrl extends Table {
    String sectionTitle;
    Image logoImage;
    Label aeFunLabel;
    Label sectionLabel;

    public String getSectionTitle() {
        return sectionTitle;
    }

    public BannerCtrl(ITheme theme, float pX, float pY, float pWidth, float pHeight, String sectionTitle) {
        super(theme, pX, pY, pWidth, pHeight);

        this.sectionTitle = sectionTitle;

        this.setPadding(10);
        this.addColumn(new PercentSpaceUsage(5, 0.5f));
        this.addColumn(new PercentSpaceUsage(5, 0.5f));

        this.addRow(new PercentSpaceUsage(5, 1));
        this.addRow(new PercentSpaceUsage(5, 1));


        this.logoImage = new Image(theme, 0, 0, 10, 10, ResourceInfosSingleton.getInstance().getBitarcherLogoMvcImage());
        this.logoImage.setAlignStyle(EnumAlignStyle.Center);
        this.attachChild(this.logoImage, 0, 0, 1, 2);

        this.aeFunLabel = new Label(theme, 0, 0, 10, 10, "aeFun");
        this.attachChild(this.aeFunLabel);

        this.sectionLabel = new Label(theme, 0, 0, 10, 10, sectionTitle);
        this.attachChild(this.sectionLabel);
    }
}
