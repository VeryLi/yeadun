package SerdeTest;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.protocol.request.OtherRequest;
import com.yeadun.bigdata.platform.protocol.ProtocolFactory;
import com.yeadun.bigdata.platform.util.LogUtil;


public class Client {

    private static LogUtil logger = new LogUtil(Client.class);
    public static void main(String[] args) throws Exception{
        PlatformContext ctx = new PlatformContext();
        ProtocolProto.ProtocolType type = ProtocolProto.ProtocolType.OTHER;
        OtherRequest req = (OtherRequest) ProtocolFactory.createRequest(ctx.getProtocol(), type);
        req.setInput("in", "This is request test. your name is --> ");

        logger.info("protocol ID -> " + ctx.getProtocol().getId());
        ctx.startClient();
        logger.info(ctx.getProtocol().getResponse().getMessageBodyList().size()+"");
        String outkey = ctx.getProtocol().getResponse().getMessageBodyList().get(0).getKey();
        String outVal = ctx.getProtocol().getResponse().getMessageBodyList().get(0).getVal();
        logger.info("Client receive result is : key -> " + outkey + ", value -> " + outVal + ".");
    }
}
