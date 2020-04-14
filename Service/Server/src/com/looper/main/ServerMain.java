package com.looper.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        //启动服务器
        ServerSocket serverSocket = new ServerSocket(5900);
        System.out.println("服务器启动......");

        while(true) {
            //监听客户端请求
            Socket socket = serverSocket.accept();

            Thread thread = new Thread(new ServerRunnable(socket));

            thread.start();

        }

    }

}
