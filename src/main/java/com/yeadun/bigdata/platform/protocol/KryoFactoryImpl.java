package com.yeadun.bigdata.platform.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.yeadun.bigdata.platform.PlatformConf;
import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.util.LogUtil;


class KryoFactoryImpl implements KryoFactory {
    private LogUtil logger = new LogUtil(KryoFactoryImpl.class);

    public KryoFactoryImpl(PlatformContext ctx){
        PlatformConf conf = ctx.getConf();
        logger.debug("kryo factory has been implemented.");
    }

    public Kryo create() {
        Kryo kryo = new Kryo();
        kryo.register(Protocol.class);
        this.logger.info("created a kryo instance for serialization");
        return kryo;
    }
}
