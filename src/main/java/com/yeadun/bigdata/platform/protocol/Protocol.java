package com.yeadun.bigdata.platform.protocol;

import com.yeadun.bigdata.platform.util.LogUtil;
import com.yeadun.bigdata.platform.util.PropUtil;

/**
 * Created by chen on 16-12-6.
 */

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
        }else if(type == MessageType.HIVE){
            this.msg = new HiveMessage();
        }else if(type == MessageType.SPARK){
            this.msg = new SparkMessage();
        }else if(type == MessageType.KAFKA){
            this.msg = new KafkaMessage();
        }else{
            this.msg = new OtherMessage();
        }
        logger.info((String)this.msg.read());
    }

    public Object getType(){
        return this.type;
    }

    public Message readMsg(){
        return this.msg;
    }

    private void writeMsg(Message msg){
        this.msg = msg;
    }
}

