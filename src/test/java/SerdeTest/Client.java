package SerdeTest;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.constructor.OtherProtocolConstructor;
import com.yeadun.bigdata.platform.util.LogUtil;


public class Client {

    private static LogUtil logger = new LogUtil(Client.class);
    public static void main(String[] args) throws Exception{
        PlatformContext ctx = new PlatformContext();
        OtherProtocolConstructor opc = new OtherProtocolConstructor(ctx);
        opc.setProtocolName("OtherProtocol")
                .setRequestName("RequsetName")
                .putInputData("k", "v")
                .flush();
        ctx.send();

        String name = ctx.getProtocol().getResponse().getMessageBodyList().get(0).getName();
        String key = ctx.getProtocol().getResponse().getMessageBodyList().get(0).getKey();
        String values = ctx.getProtocol().getResponse().getMessageBodyList().get(0).getVal();

        logger.info(name + " ----- " + key + " ----- " + values);
    }
}
