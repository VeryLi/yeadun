package SerdeTest;

import com.yeadun.bigdata.platform.PlatformContext;
import com.yeadun.bigdata.platform.server.PlatformServer;
import com.yeadun.bigdata.platform.util.LogUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    private static LogUtil logger = new LogUtil(Server.class);

    public static void main(String[] args) throws Exception{
        int port = 9999;
        logger.info("---- start server. ----");
        PlatformContext server = new PlatformContext();
        server.startServer();
    }
}
