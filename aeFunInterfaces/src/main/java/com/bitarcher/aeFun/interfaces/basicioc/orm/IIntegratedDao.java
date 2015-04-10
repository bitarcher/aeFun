/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

package com.bitarcher.aeFun.interfaces.basicioc.orm;

/**
 * Created by michel on 14/02/15.
 */
public interface IIntegratedDao {
    int i_create();
    int i_delete();
    Object i_extractId();
    int i_refresh();
    int i_update();
    int i_updateId(Object id);
}
