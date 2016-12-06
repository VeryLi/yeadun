package SerdeTest;

import com.yeadun.bigdata.platform.protocol.MessageType;
import com.yeadun.bigdata.platform.protocol.Protocol;
import com.yeadun.bigdata.platform.protocol.messages.OtherMessage;
import com.yeadun.bigdata.platform.server.PlatformConf;
import com.yeadun.bigdata.platform.server.PlatformDefaultProps;
import com.yeadun.bigdata.platform.util.LogUtil;
import com.yeadun.bigdata.platform.util.PropUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chen on 16-12-5.
 */
public class Test {
    private static LogUtil logger = new LogUtil(Test.class);
    static class User  {
        private String name;
        private int age;
        public User(){
            this.name = "Lee";
            this.age = 22;
        }
        public User(String name, int age){
            this.name = name;
            this.age = age;
        }
        public void setName(String name){
            this.name = name;
        }
        public void setAge(int age){
            this.age = age;
        }
        public String getName(){
            return this.name;
        }
        public int getAge(){
            return this.age;
        }
    }

    public static void main(String[] args){
/*        User user = new User();
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(User.class, User.class.hashCode());
        Output out = new Output(1024);
        kryo.writeClassAndObject(out, user);
        out.flush();

        Input in = new Input(out.toBytes());
        logger.info("in data ===> " + in.getBuffer().length);
        Object obj = kryo.readClassAndObject(in);
        if(obj instanceof User){
            User user2 = (User) obj;
            System.out.println(user2.getName() + " == " + user2.getAge());
        }*/

        try {
            PropUtil p = new PropUtil("conf/platform.conf");
            Map<String, String> a = p.getAllPorps();
            Iterator<String> keys = a.keySet().iterator();
            while(keys.hasNext()){
                String k = keys.next();
                logger.info(k + " => " + a.get(k));
            }
            logger.info("-------------------------------");

            PlatformConf conf = new PlatformConf();
            logger.info(conf._server_host.getStrValue());
            logger.info(conf._server_port.getIntValue() + "");
            logger.info(conf._data_delimiter.getStrValue());
            logger.info(conf._buffer_size.getIntValue() + "");
            Protocol prot = new Protocol(MessageType.OTHER);
            prot.readMsg().write("this is a test...");
            logger.info((String)(prot.readMsg()).read());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
