package com.yeadun.bigdata.platform.protocol;

public class InvalidProtocolTypeException extends Exception {
    InvalidProtocolTypeException(){
        super();
    }
    public InvalidProtocolTypeException(String msg){
        super(msg);
    }
}
