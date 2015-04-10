package com.bitarcher.aeFun.interfaces.basicioc;

import java.util.Date;

/**
 * Created by michel on 21/01/15.
 */
public interface IService {
    void start();
    void stop();
    void tic(Date dateTime);
}
