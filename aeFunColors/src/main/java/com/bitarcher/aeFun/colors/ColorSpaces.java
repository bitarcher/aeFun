package com.bitarcher.aeFun.colors;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.colors.IColorSpaces;
import com.bitarcher.aeFun.interfaces.colors.IHSB;

import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 23/04/15.
 */
public class ColorSpaces implements IColorSpaces {
    public Color HSB_to_Color(IHSB hsb)
    {
        double r = 0;
        double g = 0;
        double b = 0;

        if(hsb.getSaturation() == 0)
        {
            r = g = b = hsb.getBrightness();
        }
        else
        {
            // the color wheel consists of 6 sectors. Figure out which sector you're in.
            double sectorPos = hsb.getHue() / 60.0;
            int sectorNumber = (int)(Math.floor(sectorPos));
            // get the fractional part of the sector
            double fractionalSector = sectorPos - sectorNumber;

            // calculate values for the three axes of the color.
            double p = hsb.getBrightness() * (1.0 - hsb.getSaturation());
            double q = hsb.getBrightness() * (1.0 - (hsb.getSaturation() * fractionalSector));
            double t = hsb.getBrightness() * (1.0 - (hsb.getSaturation() * (1 - fractionalSector)));

            // assign the fractional colors to r, g, and b based on the sector the angle is in.
            switch (sectorNumber)
            {
                case 0:
                    r = hsb.getBrightness();
                    g = t;
                    b = p;
                    break;
                case 1:
                    r = q;
                    g = hsb.getBrightness();
                    b = p;
                    break;
                case 2:
                    r = p;
                    g = hsb.getBrightness();
                    b = t;
                    break;
                case 3:
                    r = p;
                    g = q;
                    b = hsb.getBrightness();
                    break;
                case 4:
                    r = t;
                    g = p;
                    b = hsb.getBrightness();
                    break;
                case 5:
                    r = hsb.getBrightness();
                    g = p;
                    b = q;
                    break;
            }
        }

        Color retval = new Color((float)r, (float)g, (float)b);
        return retval;
    }


    public IHSB color_to_HSB(Color color)
    {
        double r = ((double)color.getRed());
        double g = ((double)color.getGreen());
        double b = ((double)color.getBlue());

        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));

        double h = 0.0;
        if(max==r && g>=b)
        {
            if(max-min == 0) h = 0.0;
            else h = 60 * (g-b)/(max-min);
        }
        else if(max==r && g < b)
        {
            h = 60 * (g-b)/(max-min) + 360;
        }
        else if(max == g)
        {
            h = 60 * (b-r)/(max-min) + 120;
        }
        else if(max == b)
        {
            h = 60 * (r-g)/(max-min) + 240;
        }

        double s = (max == 0)? 0.0 : (1.0-((double)min/(double)max));

        return new HSB((float)h, (float)s, (float)max);
    }
}
