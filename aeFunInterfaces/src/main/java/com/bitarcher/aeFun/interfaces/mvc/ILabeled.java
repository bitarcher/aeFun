package com.bitarcher.aeFun.interfaces.mvc;

/**
 * Created by michel on 20/01/15.
 */
public interface ILabeled {
    String getTranslatedLabel();

    void addLabeledListener(ILabeledListener labeledListener);
    void removeLabeledListener(ILabeledListener labeledListener);
}
