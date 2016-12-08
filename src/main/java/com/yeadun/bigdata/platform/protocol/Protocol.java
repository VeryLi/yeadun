package com.yeadun.bigdata.platform.protocol;

import com.yeadun.bigdata.platform.util.LogUtil;

public class Protocol {

    private MessageType type;
    private Message msg;
    private LogUtil logger = new LogUtil(Protocol.class);

    public Protocol(MessageType type){
        this.type = type;
        if(type == MessageType.HDFS){
            this.msg = new HDFSMessage();
        }else if(type == MessageType.HBASE){
            this.msg = new HbaseMessage();
        }else if(type == MessageType.SPARK){
            this.msg = new SparkMessage();
        }else{
            this.msg = new OtherMessage();
        }
    }

    public Object getType(){
        return this.type;
    }

    public void writeMessage(Object msg){
        this.msg.write(msg);
    }

    public Object readMessage(){
        return this.msg.read();
    }

    public void execute(){
        this.msg.execute();
    }

    /**
     * Message
     * */
    abstract class Message {
        // this message has been executed, or not .
        boolean finished;
        // message type.
        MessageType type;
        public abstract void write(Object msg);
        public abstract Object read();
        public abstract void execute();
        public MessageType getType(){return this.type;}
        public boolean isFinished(){
            return finished;
        }
    }

    /**
     * Hbase Message
     * */
    private class HbaseMessage extends Message {
        public void write(Object msg) {

        }

        public Object read() {
            return null;
        }

        public void execute() {

        }
    }

    /**
     * HDFS Message
     * */
    private class HDFSMessage extends Message {
        public void write(Object msg) {

        }

        public Object read() {
            return null;
        }

        public void execute() {

        }
    }

    /**
     * Spark Message
     * */
    private class SparkMessage extends Message {
        public void write(Object msg) {}

        public Object read() {
            return null;
        }

        public void execute() {

        }
    }

    /**
     * Other Message
     * */
    private class OtherMessage extends Message {

        private String in;
        private String tmp;
        private String out;
        private MessageType type;
        public OtherMessage(){
            this.type = MessageType.OTHER;
        }
        public void write(Object msg) {
            this.in = (String)msg;
            this.tmp = this.in;
        }

        public Object read() {
            return this.out;
        }

        public void execute() {
            this.out = this.tmp;
        }
    }

}

