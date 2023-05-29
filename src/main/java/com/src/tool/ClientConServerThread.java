package com.src.tool;

import com.src.common.Message;
import com.src.common.MessageType;
import com.src.view.QqChat;
import com.src.view.QqFriendList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 功能
 *
 * @author caojianbang
 * @date 21/12/2021 10:11
 */
public class ClientConServerThread extends Thread{
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ClientConServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        while(true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message m = (Message) ois.readObject();
                if (m.getMesType().equals(MessageType.message_comm_mes)){
                    QqChat qqChat = ManageQqChat.getQqChat(m.getReceiver()+""+m.getSender());
                    qqChat.showMessage(m);
                }else if(m.getMesType().equals(MessageType.message_get_onlineFriend)){
                    String con = m.getCon();
                    String friend[]= con.split(" ");
                    String getter = m.getReceiver();
                    //获取好友列表
                    QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);
                    if (friend!=null){
                        qqFriendList.updateFriendList(m);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
            }

        }

    }
}
