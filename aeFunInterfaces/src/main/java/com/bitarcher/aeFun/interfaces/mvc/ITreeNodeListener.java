package com.bitarcher.aeFun.interfaces.mvc;

/**
 * Created by michel on 20/01/15.
 */
public interface ITreeNodeListener {
    void onChildAdded(ITreeNode parent, ITreeNode addedChild);
    void onChildRemoved(ITreeNode parent, ITreeNode removedChild);
}
