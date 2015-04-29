package com.bitarcher.aeFun.interfaces.colors;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import org.andengine.util.adt.color.Color;

/**
 * Created by michel on 29/04/15.
 */
public interface IRandomColors {
    Color getRandomColor();

    /** between colors segment
     *
     * @param c1 first bound
     * @param c2 second bound
     * @return random color
     */
    Color getRandomColor(Color c1, Color c2);

    /**
     * between colors triangle
     * @param c1
     * @param c2
     * @param c3
     * @return
     */
    Color getRandomColor(Color c1, Color c2, Color c3);

    /**
     * Between colors solid
     * @param c1
     * @param c2
     * @param c3
     * @param c4
     * @return
     */
    Color getRandomColor(Color c1, Color c2, Color c3, Color c4);
}
