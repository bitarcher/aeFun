package com.bitarcher.aeFun.interfaces.mvc;

import java.util.List;

/**
 * Created by michel on 20/01/15.
 */
public interface IList {
    List<IImageColumn> getImageColumnList();
    List<ITextColumn> getTextColumnList();
    List<IListItem> getItemList();

    void addListListener(IListListener listListener);
    void removeListListener(IListListener listListener);
}
