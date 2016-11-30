package SerdeTest;

import com.yeadun.bigdata.platform.util.LogUtil;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chen on 16-11-29.
 */
public class Server {
    private static ServerSocket srvSocket;
    private static Socket socket;
    private static LogUtil logger = new LogUtil(Server.class);

    public static void main(String[] args){
        int port = 9999;
        try {
            srvSocket = new ServerSocket(port);
            logger.info("socket server start.");
            while(true) {
                socket = srvSocket.accept();
                logger.info("new connection accepted => " + socket.getInetAddress() + ":" + socket.getPort() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                logger.info("closing ServerSocket and socket.");
                srvSocket.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
