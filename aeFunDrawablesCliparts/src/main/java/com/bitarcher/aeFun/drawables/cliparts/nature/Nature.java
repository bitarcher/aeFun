package com.bitarcher.aeFun.drawables.cliparts.nature;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 21/04/15.
 */
public class Nature {
    Trees trees;

    public Trees getTrees() {
        return trees;
    }

    public Nature() {
        this.trees = new Trees(this);
    }
}
