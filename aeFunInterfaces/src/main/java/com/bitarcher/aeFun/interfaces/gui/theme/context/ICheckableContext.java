package com.bitarcher.aeFun.interfaces.gui.theme.context;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.basicioc.IClickableEntity;
import com.bitarcher.aeFun.interfaces.gui.theme.context.setter.ICheckedSetter;
import com.bitarcher.aeFun.interfaces.gui.theme.context.setter.ITranslatedLabelSetter;

/**
 * Created by michel on 20/03/15.
 */
public interface ICheckableContext extends ICommonContext, ICheckedSetter, ITranslatedLabelSetter {
    IClickableEntity getClickableEntity();
}
