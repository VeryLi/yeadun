package com.bigdata.platform.exception;

import com.bigdata.platform.client.ClientPlatformContext;
import com.bigdata.platform.protocol.ResponseProto;
import com.bigdata.platform.protocol.constructor.OtherProtocolConstructor;
import com.bigdata.platform.util.LogUtil;

/**
 * Client development demo.
 * */
public class Client {

    private static LogUtil logger = new LogUtil(Client.class);
    public static void main(String[] args) throws Exception{
        // 1. create ClientPlatformContext.
        ClientPlatformContext ctx = new ClientPlatformContext();

        // 2. according to your request, create corresponding ProtocolConstructor.
        OtherProtocolConstructor opc = new OtherProtocolConstructor(ctx);

        // 3. use ProtocolConstructor to construct your Protocol and Request.
        opc.setProtocolName("OtherProtocol")
                .setRequestName("RequsetName")
                .putInputData("k", "v")
                .flush();

        // 4. start PlatformClient, and send Request to PlatformServer.
        ctx.send();

        // 5. when PlatformServer has handle Request,
        // the Response will be write into PlatformContext.
        ResponseProto.response response = ctx.getProtocol().getResponse();

        String name = response.getMessageBodyList().get(0).getName();
        String key = response.getMessageBodyList().get(0).getKey();
        String values = response.getMessageBodyList().get(0).getVal();
        System.out.println("Response message is -> [Response Name] " + name + ", [ResponseKey] " + key + ", [ResponseValue] " + values + ".");
        logger.info("Response message is -> [Response Name] " + name + ", [ResponseKey] " + key + ", [ResponseValue] " + values + ".");
    }
}
