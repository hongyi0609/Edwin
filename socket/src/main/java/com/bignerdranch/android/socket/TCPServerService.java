package com.bignerdranch.android.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by hongyi_000 on 2017/7/15.
 */

public class TCPServerService extends Service {

    private static final String TAG = TCPServerService.class.getClass().getSimpleName();
    private boolean mIsServiceDestroyed = false;
    private String[] mDefinedMessages = new String[]{
            "你好啊，哈哈",
            "请问你叫什么？",
            "今天上海好热，宝贝",
            "你知道吗，全国的天气都很热",
            "真的吗？",
            "是呀！",
            "这样的天气应该多运动，哈哈",
            "为啥呢？",
            "适合减肥"
    };


    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {

        @SuppressWarnings("resource")
        @Override
        public void run() {
            ServerSocket serverSocket;
            try {
                //监听8688端口
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                Log.d(TAG, "establish tcp server failed, :8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestroyed){
                //接收客户端请求
                try {
                    final Socket client = serverSocket.accept();
                    Log.d(TAG, "accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void responseClient(Socket client) throws IOException {
            // 用于接收客户端消息
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            // 用于向客户端发送消息
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream())), true);
            out.println("Welcome to chat room!");
            while (!mIsServiceDestroyed){
                String str = in.readLine();
                Log.d(TAG, "msg from client:" + str);
                if (str == null){
                    // 客户端断开连接
                    break;
                }
                int i = new Random().nextInt(mDefinedMessages.length);
                String msg = mDefinedMessages[i];
                out.println(msg);
                Log.d(TAG, "send msg: " + msg);
            }
            Log.d(TAG, "client quit.");
            // 关闭流
            out.close();
            in.close();
            client.close();
        }
    }
}
