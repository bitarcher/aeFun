package com.bitarcher.aeFun.interfaces.mvc;

import com.bitarcher.aeFun.interfaces.basicioc.INamed;

import java.util.List;

/**
 * Created by michel on 20/01/15.
 */
public interface ITreeNode extends ILabeled, INamed {
    ITreeNode getParent();
    List<ITreeNode> getChildNodes();
    void addTreeNodeListener(ITreeNodeListener listener);
    void removeTreeNodeListener(ITreeNodeListener listener);
    String getPath();
}
