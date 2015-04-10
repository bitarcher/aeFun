package com.bitarcher.aeFun.interfaces.mvc;

/**
 * Created by michel on 22/01/15.
 */
public interface ILabeled_Edit extends ILabeled {
    void setTranslatedLabel(String translatedLabel);

    void addLabeledListener(ILabeledListener labeledListener);
    void removeLabeledListener(ILabeledListener labeledListener);
}
