package com.yeadun.bigdata.platform.server;

/**
 * Created by chen on 16-12-6.
 */
public enum PlatformDefaultProps {
    // platform properties
    SERVER_HOST("localhost"),
    SERVER_PORT("9999"),
    BUFFER_SIZE("2048"),
    DATA_DELIMITER("$_"),
    SO_BACKLOG("100"),
    CLIENT_CONNECT_TIMEOUT("5000"),
    SER_BUFFER_SIZE("2048"),
    DESER_BUFFER_SIZE("2048");

    private String value;
    PlatformDefaultProps(String value){
        this.value = value;
    }

    public String getValue(){
        return  this.value;
    }

    public String getStrValue(){
        return this.value;
    }

    public int getIntValue(){
        return Integer.parseInt((String) this.value);
    }

    public float getFloatValue(){
        return Float.parseFloat((String) this.value);
    }

    public PlatformDefaultProps setValue(String value){
        this.value = value;
        return this;
    }
}
