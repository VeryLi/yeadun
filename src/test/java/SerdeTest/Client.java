package SerdeTest;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.MessageProto;
import com.yeadun.bigdata.platform.util.LogUtil;


public class Client {

    private static LogUtil logger = new LogUtil(Client.class);
    public static void main(String[] args) throws Exception{
        PlatformContext ctx = new PlatformContext();
        ctx.getProtocolBuilder().setName("this is test.");
        ctx.getProtocolBuilder().setId(110);
        ctx.getProtocolBuilder().setName("MyTestClient");
        ctx.getProtocolBuilder().getRequestBuilder().setType(MessageProto.MessageType.REQUEST);
        ctx.startClient();

    }
}
