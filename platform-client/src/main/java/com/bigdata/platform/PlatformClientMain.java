package com.bigdata.platform;

import com.bigdata.platform.client.ClientPlatformContext;
import com.bigdata.platform.protocol.ResponseProto;
import com.bigdata.platform.protocol.constructor.OtherProtocolConstructor;
import com.bigdata.platform.util.LogUtil;

import java.util.UUID;

public class PlatformClientMain {

    private static LogUtil logger = new LogUtil(PlatformClientMain.class);
    public static void main(String[] args) throws Exception{
        // 1. create ClientPlatformContext.
        ClientPlatformContext ctx = new ClientPlatformContext();

        // 2. according to your request, create corresponding ProtocolConstructor.
        OtherProtocolConstructor opc = new OtherProtocolConstructor(ctx);

        // 3. use ProtocolConstructor to construct your Protocol and Request.
        String key = UUID.randomUUID().toString().replace("-", "");
        String values = UUID.randomUUID().toString().replace("-", "");
        opc.setProtocolName("OtherProtocol")
                .setRequestName("RequsetName")
                .putInputData(key, values)
                .flush();

        // 4. start com.bigdata.platform.PlatformClientMain, and send Request to PlatformServer.
        ctx.send();

        // 5. when PlatformServer has handle Request,
        // the Response will be write into PlatformContext.
        ResponseProto.response response = ctx.getProtocol().getResponse();

        String name = response.getMessageBodyList().get(0).getName();
        String key1 = response.getMessageBodyList().get(0).getKey();
        String values1 = response.getMessageBodyList().get(0).getVal();
        logger.info("Response message is -> [Response Name] " + name + ", [ResponseKey] " + key1 + ", [ResponseValue] " + values1 + ".");
    }

}
