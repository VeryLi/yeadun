package SerdeTest;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.protocol.MessageType;
import com.yeadun.bigdata.platform.protocol.Protocol;
import com.yeadun.bigdata.platform.util.LogUtil;


public class Client {

    private static LogUtil logger = new LogUtil(Client.class);
    public static void main(String[] args) throws Exception{
        PlatformContext ctx = new PlatformContext();
        Protocol request = new Protocol(MessageType.OTHER);
        request.writeMessage("[this is my test.]");
        ctx.writeRequestProtocol(request);
        ctx.startClient();

    }
}
