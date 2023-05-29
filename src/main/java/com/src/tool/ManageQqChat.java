package com.src.tool;

import com.src.view.QqChat;

import java.util.HashMap;

/**
 * 功能
 *
 * @author caojianbang
 * @date 21/12/2021 10:14
 */
public class ManageQqChat {
    private static HashMap hm = new HashMap<String,QqChat>();
    public static void addQqChat(String loginIdAndFriend,QqChat qqChat){
        hm.put(loginIdAndFriend,qqChat);
    }
    public static QqChat getQqChat(String loginIdAndFriend){
       return (QqChat) hm.get(loginIdAndFriend);
    }
}
