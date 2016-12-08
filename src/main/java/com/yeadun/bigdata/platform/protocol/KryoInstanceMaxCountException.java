package com.yeadun.bigdata.platform.protocol;

/**
 * Created by chen on 16-12-8.
 */
public class KryoInstanceMaxCountException extends Error {
    public KryoInstanceMaxCountException(){}
    public KryoInstanceMaxCountException(String msg) {
        super(msg);
    }
}
