package com.src.tool;

import com.src.view.QqFriendList;

import java.util.HashMap;

/**
 * 功能
 *
 * @author caojianbang
 * @date 21/12/2021 11:52
 */
public class ManageQqFriendList {
    private static HashMap hm = new HashMap<String,QqFriendList>();
    public  static void addQqFriendList(String qqId,QqFriendList qqFriendList){
        hm.put(qqId,qqFriendList);
    }
    public static QqFriendList getQqFriendList(String qqId){
        return  (QqFriendList)hm.get(qqId);
    }
}
