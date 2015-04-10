package com.bitarcher.aeFun.interfaces.gui.theme.context;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */


import com.bitarcher.aeFun.interfaces.gui.theme.context.setter.IEnabledSetter;

import com.bitarcher.aeFun.interfaces.gui.theme.context.setter.IPaddingSetter;
import com.bitarcher.aeFun.interfaces.gui.theme.context.setter.ISizeSetter;

/**
 * Created by michel on 09/03/15.
 */
public interface ICommonContext  extends IContext, IEnabledSetter, ISizeSetter, IPaddingSetter {
}
