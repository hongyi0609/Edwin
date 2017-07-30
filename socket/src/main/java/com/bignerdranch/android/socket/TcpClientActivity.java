package com.bignerdranch.android.socket;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpClientActivity extends Activity {

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;
    private static final String TAG = TcpClientActivity.class.getSimpleName();

    private Button mSendButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;
    private WebView mWebView;

    private PrintWriter mPrintWriter;
    private Socket mClientSocket;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_RECEIVE_NEW_MSG:{
                    mMessageTextView.setText(mMessageTextView.getText() + (String) msg.obj);
                    break;
                }
                case MESSAGE_SOCKET_CONNECTED:
                    mSendButton.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_layout);
        mMessageTextView = (TextView) findViewById(R.id.msg_container);

        mSendButton = (Button) findViewById(R.id.send);
        mSendButton.setOnClickListener(l);

        mMessageEditText = (EditText) findViewById(R.id.msg);
        Intent service = new Intent(this, TCPServerService.class);
        startService(service);

        new Thread(){
            @Override
            public void run() {
                connectTcpServer();
            }
        }.start();

        mWebView = (WebView) findViewById(R.id.webview_test);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(ua+";iSnow");//自定义标记

        mWebView.loadUrl("http://blog.csdn.net");

        mWebView.setWebViewClient(new WebViewClient(){
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                if("blog.csdn.net".equals(Uri.parse(url).getHost())){
                    view.loadUrl("https://www.baidu.com");
                }
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return super.shouldOverrideUrlLoading(view, request);
                String userAgent = view.getSettings().getUserAgentString();
                Log.d(TAG, "userAgent = " + userAgent);
                if (userAgent.substring(userAgent.length()-";iSnow".length()).equals(";iSnow")){
                    Log.d(TAG, "userAgent >>>>>>>>>>>>>> ##########");
                    view.getSettings().setUserAgentString(userAgent.substring(0, userAgent.length()-";iSnow".length()));
                    view.loadUrl("https://www.baidu.com");
                    return false;
                }
                /**
                 * 如下方案无法解决拦截Url时出现的重定向问题 <br />
                 * */
//                Log.d(TAG, "request.getUrl = " + request.getUrl() +
//                "," + Uri.parse(request.getUrl().toString()).getHost());
//                if("m.blog.csdn.net".equals(Uri.parse(request.getUrl().toString()).getHost())){
//                    view.loadUrl("https://www.baidu.com");
//                }
                return false;
            }

            @SuppressWarnings("deprecation")
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                WebResourceResponse response = null;
                if (url.contains("logo")) {
                    try {
                        InputStream logo = getAssets().open("logo.png");
                        response = new WebResourceResponse("image/png", "UTF-8", logo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return response;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                /**
                 * 实现Android N 拦截网页logo功能<br />
                 * 比如拦截百度的熊掌logo<br />
                 * */
                WebResourceResponse response = null;
                String url = request.getUrl().toString();
                if (url.contains("logo")) {
                    try {
                        InputStream logo = getAssets().open("logo.png");
                        response = new WebResourceResponse("image/png", "UTF-8", logo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return response;
            }
        });
    }



    @Override
    protected void onDestroy() {
        if (mClientSocket != null){
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onDestroy();
    }

    private void connectTcpServer() {
        Socket socket = null;
        while (socket == null){
            try {
                socket = new Socket("localhost",8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                System.out.println("connect server success");
            } catch (IOException e) {
                // 超时重连
                SystemClock.sleep(1000);
                System.out.println("connect server failed, retry ...");
            }
        }

        try {
            // 接收服务器的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            while (!TcpClientActivity.this.isFinishing()){
                String msg = br.readLine();
                System.out.println("receive :" + msg);
                if (msg != null){
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showMsg = "server " + time + ":" + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showMsg).sendToTarget();
                }
            }
            System.out.println("quit ...");
            mPrintWriter.close();
            br.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SimpleDateFormat")
    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    private OnClickListener l = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final String msg = mMessageEditText.getText().toString();
            if (!TextUtils.isEmpty(msg) && mPrintWriter != null){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 网络操作，不能直接在主线程，否则会报网络在主线程异常，任玉刚这里遗漏了
                        mPrintWriter.println(msg);
                    }
                }).start();
                mMessageEditText.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                final String showedMsg = "self " + time + ":" + msg + "\n";
                mMessageTextView.setText(mMessageTextView.getText() + showedMsg);
            }
        }
    };


}
