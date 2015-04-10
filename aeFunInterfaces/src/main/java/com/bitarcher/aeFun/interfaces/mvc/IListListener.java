package com.bitarcher.aeFun.interfaces.mvc;

/**
 * Created by michel on 20/01/15.
 */
public interface IListListener {
    void onListItemAdded(IList list, IListItem listItem);
    void onListItemRemoved(IList list, IListItem listItem);
}
