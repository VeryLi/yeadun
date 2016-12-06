package com.yeadun.bigdata.platform.protocol;

/**
 * Created by chen on 16-12-6.
 */
abstract class Message {
    // this message has been executed, or not .
    protected boolean finished;
    protected MessageType type;
    protected MessageType getType(){
        return this.type;
    }
    public abstract void write(Object msg);
    public abstract Object read();
}
/***
 *  Other Message
 */
class OtherMessage extends Message {
    private String req;
    private String resp;
    public OtherMessage(){
        super();
        this.type = MessageType.OTHER;
    }

    public void write(Object msg) {
        this.req = (String) msg;
    }

    public Object read() {
        return this.resp;
    }
}

/***
 *  HDFS Message
 */
class HDFSMessage extends Message {
    public void write(Object msg) {

    }

    public Object read() {
        return null;
    }
}

/***
 *  HBASE Message
 */
class HbaseMessage extends Message {
    public void write(Object msg) {

    }

    public Object read() {
        return null;
    }
}

/***
 *  Kafka Message
 */
class KafkaMessage extends Message {
    public void write(Object msg) {

    }

    public Object read() {
        return null;
    }
}

/***
 *  SPARK Message
 */
class SparkMessage extends Message {
    public void write(Object msg) {}

    public Object read() {
        return null;
    }
}

/***
 *  Hive Message
 */
class HiveMessage extends Message {
    public void write(Object msg) {

    }

    public Object read() {
        return null;
    }
}
