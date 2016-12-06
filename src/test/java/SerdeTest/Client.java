package SerdeTest;

import com.yeadun.bigdata.platform.client.PlatformClient;
import com.yeadun.bigdata.platform.util.LogUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * Created by chen on 16-11-30.
 */
public class Client {

    private static LogUtil logger = new LogUtil(Client.class);
    public static void main(String[] args) throws Exception{

        int port = 9999;
        String host = "localhost";
        new PlatformClient().run();
    }
}
