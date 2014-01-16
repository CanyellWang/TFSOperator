package me.lilei.tfs.config.model;

public class TFSConfig {

    public String NAME_SERVER_ADDRESS;
    public int THREAD_COUNT;
    public String getNAME_SERVER_ADDRESS() {
        return NAME_SERVER_ADDRESS;
    }
    public void setNAME_SERVER_ADDRESS(String nAME_SERVER_ADDRESS) {
        NAME_SERVER_ADDRESS = nAME_SERVER_ADDRESS;
    }
    public int getTHREAD_COUNT() {
        return THREAD_COUNT;
    }
    public void setTHREAD_COUNT(int tHREAD_COUNT) {
        THREAD_COUNT = tHREAD_COUNT;
    }
}
