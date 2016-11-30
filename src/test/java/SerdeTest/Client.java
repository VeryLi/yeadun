package SerdeTest;

import com.yeadun.bigdata.platform.util.LogUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by chen on 16-11-30.
 */
public class Client {

    private static LogUtil logger = new LogUtil(Client.class);
    public static void main(String[] args) throws Exception{
        Thread th1 = new Thread(new client(9999));
        Thread th2 = new Thread(new client(9999));
        th1.start();
        Thread.sleep(5000);
        th2.start();
    }

    private static class client implements Runnable{

        private int port;
        private Socket socket;
        client (int port){
            this.port = port;
        }
        public void run() {
            try {
                this.socket = new Socket("localhost", this.port);
                while(true){

                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
