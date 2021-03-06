package com.bigdata.platform.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {
    private Logger logger;
    public LogUtil(Class cls){
        this.logger = Logger.getLogger(cls);
        String confPath = System.getProperty("platform.conf.dir");
        PropertyConfigurator.configure(confPath + "/log4j.properties");
    }

    public void info(String msg){
        this.logger.info(msg);
    }

    public void info(String msg, Throwable t){
        this.logger.info(msg, t);
    }

    public void err(String msg){
        this.logger.error(msg);
    }

    public void err(String msg, Throwable t){
        this.logger.error(msg, t);
    }

    public void debug(String msg){
        this.logger.debug(msg);
    }

    public void debug(String msg, Throwable t){
        this.logger.debug(msg, t);
    }

    public void warn(String msg){
        this.logger.warn(msg);
    }

    public void warn(String msg, Throwable t){
        this.logger.warn(msg, t);
    }

    public void fatal(String msg){
        this.logger.fatal(msg);
    }

    public void fatal(String msg, Throwable t){
        this.logger.fatal(msg, t);
    }

}
