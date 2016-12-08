package com.yeadun.bigdata.platform;

/**
 * Created by chen on 16-12-6.
 */
public enum PlatformDefaultConf {
    // platform properties
    SERVER_HOST("localhost"),
    SERVER_PORT("9999"),
    SO_BACKLOG("100"),
    CLIENT_CONNECT_TIMEOUT("5000"),
    SER_BUFFER_SIZE("2048"),
    DESER_BUFFER_SIZE("2048"),
    KRYO_MAX_TOTAL("100");

    private String value;
    PlatformDefaultConf(String value){
        this.value = value;
    }

    public String getValue(){
        return  this.value;
    }

    public String getStrValue(){
        return this.value;
    }

    public int getIntValue(){
        return Integer.parseInt(this.value);
    }

    public float getFloatValue(){
        return Float.parseFloat((String) this.value);
    }

    public long getLongValue(){
        return Long.parseLong(this.value);
    }

    public PlatformDefaultConf setValue(String value){
        this.value = value;
        return this;
    }
}
