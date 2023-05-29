package com.src.view;

import com.src.common.Message;
import com.src.common.MessageType;
import com.src.model.QqClientConServer;
import com.src.tool.ManageClientConServerThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 功能
 *
 * @author caojianbang
 * @date 20/12/2021 11:34
 */
public class QqChat extends JFrame implements ActionListener{
    JTextArea jta;
    JScrollPane jsp;
    JTextField jtf ;
    JButton jb;
    JPanel jp;
    String ownId,friendId;

    public static void main(String[] args) {
       // QqChat qqChat = new QqChat();
    }

    public QqChat(String ownId,String friendId) {
        this.ownId =  ownId;
        this.friendId=friendId;
        jta = new JTextArea();
        jsp = new JScrollPane(jta);
        jp = new JPanel();
        jtf = new JTextField(25);
        jb = new JButton("发送");
        jb.addActionListener(this);
        jp.add(jtf);
        jp.add(jb);
        this.add(jsp);
        this.add(jp,"South");
        this.setTitle(ownId+"正在与"+friendId+"正在聊天");
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public void showMessage(Message m){
        jta.append(m.getSender()+":"+"\r\n"+m.getCon()+"\r\n");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb){
            Message m= new Message();
            m.setMesType(MessageType.message_comm_mes);
            m.setSender(this.ownId);
            m.setReceiver(this.friendId);
            m.setCon(this.jtf.getText());
            m.setSendTime(new Date().toString());
            jta.append(ownId+""+"\r\n"+this.jtf.getText()+"\r\n");
            jtf.setText("");
            try {
                ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownId).getSocket().getOutputStream());
                oos.writeObject(m);
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
            }

        }

    }


}
