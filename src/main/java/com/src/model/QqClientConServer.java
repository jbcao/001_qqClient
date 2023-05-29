package com.src.model;


import com.src.common.Message;
import com.src.tool.ClientConServerThread;
import com.src.tool.ManageClientConServerThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * 功能
 *
 * @author caojianbang
 * @date 20/12/2021 12:53
 */
public class QqClientConServer {
    public  Socket s;

    public boolean sendLoginInfoToServer(Object o) {
        boolean b = false;
        try {
            s = new Socket("127.0.0.1", 9999);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Message m = (Message) ois.readObject();
            if (m.getMesType().equals("1")) {
                b = true;
                //创建qq和服务器保持通信的线程
                ClientConServerThread ccst = new ClientConServerThread(s);
                ccst.start();
                ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(),ccst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return b;

    }
}
