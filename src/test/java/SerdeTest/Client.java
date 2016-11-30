package SerdeTest;

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
        client(9999);
        client(9999);
    }

    public static void client (int port)  {

        SocketChannel sc = null;
        try {
            sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("localhost", port));
            logger.info("Client : " + sc.socket().getInetAddress() + ", prot : " + sc.socket().getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            try {
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
