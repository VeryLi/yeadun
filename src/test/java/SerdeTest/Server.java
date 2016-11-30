package SerdeTest;

import com.yeadun.bigdata.platform.util.LogUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by chen on 16-11-29.
 */
public class Server {
    private static LogUtil logger = new LogUtil(Server.class);

    public static void main(String[] args) throws Exception{
        int port = 9999;
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ServerSocket serverSocket = ssc.socket();
        serverSocket.bind(new InetSocketAddress(port));
        ssc.configureBlocking(true);
        while(true){
            SocketChannel sc = ssc.accept();
            if (sc == null) {

            }else{
                if(sc.isConnected()){
                    logger.info("current connection is => " + sc.socket().getInetAddress() + ":" + sc.socket().getPort());
                }
            }
        }

    }
}
