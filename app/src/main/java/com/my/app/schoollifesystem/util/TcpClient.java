package com.my.app.schoollifesystem.util;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.my.app.schoollifesystem.bean.Message;

public class TcpClient implements Runnable{

	private static volatile Socket socket = null;
    private BufferedReader in = null;  
    private static volatile PrintWriter out = null;  
    public volatile static boolean inited = false;
    private static boolean isClose = false;

    Handler myHandler;

	public void setMyHandler(Handler myHandler) {
		this.myHandler = myHandler;
	}

	public static void closeConnect() {
		System.out.println("1");
		out.println("exit");
		try {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
			isClose = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private TcpClient(){}

	public static TcpClient instance(){
		return A.tcpClient;
	}

	private static class A{
		static TcpClient tcpClient;
		static {
			tcpClient = new TcpClient();
		}
	}

	public void sendMsg(String msg,String name) {
		if(out!=null){
			Message m = new Message();
			m.setContent(msg);
			m.setUserNo("111");
			m.setName(name);
			out.println(JSON.toJSON(m));
		}
	}

	@Override
	public void run() {
		connect();
		while(true) {
			while (true && !isClose) {//死循环守护，监控服务器发来的消息  
                if (!socket.isClosed()) {//如果服务器没有关闭  
                    if (socket.isConnected()) {//连接正常  
                        if (!socket.isInputShutdown()) {//如果输入流没有断开  
                            String getLine;  
                            try {
								if ((getLine = in.readLine()) != null) {//读取接收的信息  
								    getLine += "\n";  
								    Message msg = null;
									try {
										Gson gson = new Gson();
										msg = gson.fromJson(getLine, Message.class);
									    System.out.println(msg.getName()+"说："+msg.getContent());
									    android.os.Message message = myHandler.obtainMessage();
									    message.obj = msg;
									    message.what = 1;
									    myHandler.sendMessage(message);
									} catch (JsonSyntaxException e) {
										android.os.Message message = myHandler.obtainMessage();
										message.obj = getLine;
										message.what = 2;
										myHandler.sendMessage(message);
									}
								} else {  
  
								}
							} catch (IOException e) {
								e.printStackTrace();
							}  
                        }  
                    }  
                }  
            }  
		}
	}

	private void connect() {
		try {  
            socket = new Socket(Constant.IP_ADDRESS, Constant.PORT);//连接服务器
            in = new BufferedReader(new InputStreamReader(socket  
                    .getInputStream()));//接收消息的流对象  
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(  
                    socket.getOutputStream())), true);//发送消息的流对象  
            inited = true;
        } catch (IOException ex) {  
            ex.printStackTrace();  
            System.out.println("连接服务器失败：" + ex.getMessage());  
        }  		
	}
	
	
	
}
