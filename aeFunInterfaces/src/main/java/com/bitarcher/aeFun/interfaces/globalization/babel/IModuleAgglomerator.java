package com.bitarcher.aeFun.interfaces.globalization.babel;

import com.bitarcher.aeFun.interfaces.basicioc.ITModuleAgglomerator;

/**
 * Created by michel on 20/01/15.
 */
public interface IModuleAgglomerator extends ITModuleAgglomerator<IModuleAgglomerator> {
    String translate(Object applicant, String fmt, Object ... args);

    void addBabelModuleAgglomeratorListener(IBabelModuleAgglomeratorListener babelModuleAgglomeratorListener);
    void removeBabelModuleAgglomeratorListener(IBabelModuleAgglomeratorListener babelModuleAgglomeratorListener);
}
