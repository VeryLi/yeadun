package com.yeadun.bigdata.platform.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 16-12-5.
 */
public class PropUtil {
    private LogUtil logger = new LogUtil(PropUtil.class);
    private String ConfPath = "conf/platform.conf";
    private File confFile;
    private BufferedReader confReader;
    public PropUtil() throws IOException {
        this.confFile = new File(this.ConfPath);
        if(!this.confFile.isFile() || this.confFile == null){
            this.logger.err("conf/platform.conf dose not exist.");
            throw new IOException("conf/platform.conf dose not exist.");
        }
    }

    public void init(){
        try {
            this.confReader = new BufferedReader(new FileReader(this.confFile));
        } catch (FileNotFoundException e) {
            logger.err(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getPropWithKey(String key){
        init();
        String data = null;
        try {
            while((data = this.confReader.readLine()) != null){
                String k = data.split("=")[0].trim();
                if(k.equals(key)){
                    return data.split("=")[1].trim();
                }
            }
        } catch (IOException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                this.confReader.close();

            } catch (IOException e) {
                this.logger.err(e.getMessage());
                e.printStackTrace();
            }
        }
        return data;
    }

    public Map<String, String> getAllPorps(){
        init();
        Map<String, String> data = new HashMap<String, String>();
        String temp;
        try {
            while((temp = this.confReader.readLine()) != null){
                data.put(temp.split("=")[0].trim(), temp.split("=")[1].trim());
            }
        } catch (IOException e) {
            this.logger.err(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                this.confReader.close();

            } catch (IOException e) {
                this.logger.err(e.getMessage());
                e.printStackTrace();
            }
        }
        return data;
    }
}
