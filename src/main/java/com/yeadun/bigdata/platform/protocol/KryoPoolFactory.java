package com.yeadun.bigdata.platform.protocol;

import com.esotericsoftware.kryo.pool.KryoPool;
import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.util.LogUtil;

public class KryoPoolFactory {

    private KryoPool pool = null;
    private LogUtil logger = new LogUtil(KryoPoolFactory.class);
    public KryoPoolFactory(PlatformContext ctx){
        KryoFactoryImpl kryoFactory = new KryoFactoryImpl(ctx);
        this.pool = new KryoPool.Builder(kryoFactory).build();
        logger.debug("kryo pool has been created.");
    }

    KryoPool getPool(){
        return this.pool;
    }

}
