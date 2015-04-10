package com.bitarcher.aeFun.interfaces.basicioc;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

/**
 * Created by michel on 07/04/15.
 */
public interface IClickable {
    void addClickableListener(IClickableListener clickableListener);
    void removeClickableListener(IClickableListener clickableListener);
}
