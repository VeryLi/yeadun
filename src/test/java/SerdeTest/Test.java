package SerdeTest;

import com.sun.xml.internal.ws.api.WSService;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import com.yeadun.bigdata.platform.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;

import java.nio.ByteBuffer;

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

/*        try {
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
        }*/


        int a = 1132323;
        ByteBuffer buf = ByteBuffer.allocate(100);
        buf.putInt(a);
        int headlength = buf.limit();
        int headcap = buf.capacity();
        int headpos = buf.position();
        byte[] head = buf.array();
//        System.out.println("1123 -> " + head + " ; length " + headlength + " ; capacity " + headcap + " ; position " + headpos);

        String msg = "helloasdfasdfasdfasf";
        byte[] msgByte = msg.getBytes();
        System.out.println("length " + msgByte.length + " => " + (new String(msgByte)));
        ByteBuf bbf = Unpooled.buffer(4 + msgByte.length);
        bbf.writeInt(msgByte.length);
        bbf.writeBytes(msgByte);
        System.out.println(new String (bbf.copy(4, msgByte.length).array()));

        for(byte b : bbf.array()){
            System.out.print(b + " ");
        }
        System.out.println();
        System.out.println(bbf.copy(0, 4).readInt());

    }
}
