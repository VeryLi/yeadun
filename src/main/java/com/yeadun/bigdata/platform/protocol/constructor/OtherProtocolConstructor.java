package com.yeadun.bigdata.platform.protocol.constructor;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.ProtocolConstructor;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;

public class OtherProtocolConstructor extends ProtocolConstructor {
    /**
     * Client using this constructor.
     * */
    public OtherProtocolConstructor(PlatformContext ctx) {
        super(ctx, ProtocolProto.ProtocolType.OTHER);
    }

    /**
     * Server using this constructor.
     * */
    public OtherProtocolConstructor(ProtocolProto.protocol protocol){
        super(protocol);
    }

    public OtherProtocolConstructor putInputData(String key, String value){
        putRequestDataPair("TestOther", key, value);
        return this;
    }

    public OtherProtocolConstructor putOutputData(String key, String value){
        putResponseDataPair("TestOther", key, value);
        return this;
    }

    public OtherProtocolConstructor setProtocolName(String name){
        super.setProtocolName(name);
        return this;
    }

    public OtherProtocolConstructor setRequestName(String name){
        super.setRequestName(name);
        return this;
    }

    public OtherProtocolConstructor setResponseName(String name){
        super.setResponseName(name);
        return this;
    }
}
