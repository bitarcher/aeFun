package com.bitarcher.aeFun.interfaces.resourcemanagement;

/**
 * Created by michel on 08/01/15.
 */

// see http://stackoverflow.com/questions/15607060/java-interface-throw-an-exception-but-interface-implementation-does-not-throw-an
public class EResourceCreationError extends RuntimeException {
    public EResourceCreationError(String detailMessage) {
        super(detailMessage);
    }
}
