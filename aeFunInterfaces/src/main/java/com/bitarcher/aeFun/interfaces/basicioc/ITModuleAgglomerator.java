package com.bitarcher.aeFun.interfaces.basicioc;

import java.util.List;

/**
 * Created by michel on 20/01/15.
 */
public interface ITModuleAgglomerator<TModuleAgglomerator extends ITModuleAgglomerator<TModuleAgglomerator>> {
    List<TModuleAgglomerator> getSubAgglomerators();
}
