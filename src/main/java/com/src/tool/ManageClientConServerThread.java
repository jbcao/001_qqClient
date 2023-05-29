package com.src.tool;

import java.util.HashMap;

/**
 * 功能
 *
 * @author caojianbang
 * @date 21/12/2021 11:05
 */
public class ManageClientConServerThread {
    private  static HashMap hm  = new HashMap<String,ClientConServerThread>();
    public static void addClientConServerThread(String qqId,ClientConServerThread ccst){
        hm.put(qqId,ccst);
    }
    public static ClientConServerThread getClientConServerThread(String qqId){
      return (ClientConServerThread) hm.get(qqId);
    }
}
