package SerdeTest;

import com.google.protobuf.GeneratedMessage;
import com.yeadun.bigdata.platform.protocol.ProtocolProto;
import com.yeadun.bigdata.platform.util.LogUtil;
import com.yeadun.bigdata.platform.util.ProtocolInfoUtil;

import java.util.UUID;


/**
 * Created by chen on 16-12-5.
 */
public class Test {
    private static LogUtil logger = new LogUtil(Test.class);
    private static ProtocolInfoUtil infoUtil = new ProtocolInfoUtil();
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

        ProtocolProto.protocol p = ProtocolProto.protocol.getDefaultInstance();
        System.out.println(p.getName() + " -> " + p.getId());
        ProtocolProto.protocol.Builder pb = p.toBuilder();
        pb.setName("xxxxx");
        pb.setId(UUID.randomUUID().toString());
        ProtocolProto.protocol l = pb.build();
        p = pb.build();
        System.out.println(p.getName() + " -> " + p.getId());
        System.out.println(l.getName() + " -> " + l.getId());

        ProtocolProto.protocol.Builder builder = ProtocolProto.protocol.newBuilder();
        builder.setId(UUID.randomUUID().toString());
        builder.setName("tom");
        ProtocolProto.protocol protocol = builder.build();
        System.out.println(protocol.getId() + " => " + protocol.getName());

        protocol.toBuilder().setName("lee").setId(UUID.randomUUID().toString()).build();
        System.out.println(protocol.getId() + " => " + protocol.getName());
        System.out.println(protocol.toBuilder().getId() + " => " + protocol.toBuilder().getName());
        protocol = protocol.toBuilder().setName("lee").setId(UUID.randomUUID().toString()).build();
        System.out.println(protocol.getId() + " => " + protocol.getName());

        System.out.println(infoUtil.allInfo(protocol));


/*        KryoPool pool = new KryoPool.Builder(new KryoFactory() {
            public Kryo create() {
                Kryo kryo = new Kryo();
                kryo.setReferences(false);
                kryo.register(User.class);
                return kryo;
            }
        }).build();
        ByteBuf out = pool.run(new KryoCallback<ByteBuf>() {
            User user = new User();
            public ByteBuf execute(Kryo kryo) {
                Output out = new Output(10);
                kryo.writeClassAndObject(out, user);
                out.flush();
                byte[] res = out.getBuffer();
//                for(byte b : res){
//                    System.out.print(b + " ");
//                }
                System.out.println();

                ByteBuf bbf = Unpooled.buffer(4 + res.length);
                bbf.writeInt(res.length);
                bbf.writeBytes(res);
                out.close();
                return bbf;
            }
        });
//        User user = new User();
//        Kryo kryo = new Kryo();
//        kryo.setReferences(false);
//        kryo.register(User.class, User.class.hashCode());
//        Output out = new Output(1024);
//        kryo.writeClassAndObject(out, user);
//        out.flush();
//        System.out.println(out.getBuffer().length);

        ByteBuf res = out.copy(4, out.array().length - 4);
//        for(byte b : res.array()){
//            System.out.print(b + " ");
//        }
        System.out.println();
        Input in = new Input();
        in.setBuffer(res.array());
//        Kryo kryo = new Kryo();
//        kryo.register(User.class);
        Object obj = pool.borrow().readClassAndObject(in);
        in.close();
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


  /*      int a = 1132323;
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
        System.out.println(bbf.copy(0, 4).readInt());*/


    }
}

