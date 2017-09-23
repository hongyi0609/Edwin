package com.bignerdranch.android.socket;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button mButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;
    private WebView mWebView;

    private PrintWriter mPrintWriter;
    private Socket mClientSocket;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG: {
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

        new Thread() {
            @Override
            public void run() {
                connectTcpServer();
            }
        }.start();

        mWebView = (WebView) findViewById(R.id.webview_test);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/test.html");
        mWebView.addJavascriptInterface(new JsInteration(), "android");
        //        String ua = webSettings.getUserAgentString();
        //        webSettings.setUserAgentString(ua+";iSnow");//自定义标记

        if (Build.VERSION.SDK_INT >= 19) {
            /**
             * <p>
             *     4.4以上系统，在onPageFinished时再恢复图片加载<br />
             *     如果存在多张图片引用相同资源，只会加载一张
             * </p>
             * */
            webSettings.setLoadsImagesAutomatically(true);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }
        //        mWebView.loadUrl("http://blog.csdn.net");
        mWebView.setDownloadListener(new MyDownloadLister());

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // 开始加载网页时处理，如显示“开始加载”对话框
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!view.getSettings().getLoadsImagesAutomatically()) {
                    view.getSettings().setLoadsImagesAutomatically(true);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                // 提示加载失败或显示显示新的界面
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                /**
                 * webView默认不处理Https请求
                 * */
                handler.proceed(); // 接受信任所有网站的证书
                //                handler.cancel();  // 默认操作，不处理
                //                handler.handleMessage(null); // 其他处理
            }

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //                return super.shouldOverrideUrlLoading(view, url);
                if ("blog.csdn.net".equals(Uri.parse(url).getHost())) {
                    view.loadUrl("https://www.baidu.com");
                } else {
                    view.loadUrl(url);
                    return false;
                }
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //                return super.shouldOverrideUrlLoading(view, request);
                String userAgent = view.getSettings().getUserAgentString();
                Log.d(TAG, "userAgent = " + userAgent);
                if (userAgent.substring(userAgent.length() - ";iSnow".length()).equals(";iSnow")) {
                    Log.d(TAG, "userAgent >>>>>>>>>>>>>> ##########");
                    view.getSettings().setUserAgentString(userAgent.substring(0, userAgent.length() - ";iSnow".length()));
                    view.loadUrl("https://www.baidu.com");
                    return false;
                } else {
                    view.loadUrl(request.getUrl().toString());
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

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                /**
                 * 页面加载进程 <br />
                 * 进度
                 * @param view
                 * @param newProgress
                 * */
                setTitle("页面加载中，请稍后..." + newProgress + "%");
                setProgress(newProgress * 100);
                if (newProgress == 100) {
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        mButton = (Button) findViewById(R.id.click_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                //Android调用有返回值js方法
                mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.e(TAG, "onReceiveValue value=" + value);
                        Toast.makeText(getApplicationContext(),"sum(1+2) = " + value, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (null != savedInstanceState) {
            mWebView.restoreState(savedInstanceState);
        } else {
            mWebView.loadUrl("http://www.baidu.com");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
        Log.i(TAG, ">>>>>>>>> save State >>>>>>>>");
    }

    @Override
    protected void onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onDestroy();
        mWebView.stopLoading();
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
    }

    private void connectTcpServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
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
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TcpClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                System.out.println("receive :" + msg);
                if (msg != null) {
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

    public class JsInteration {
        /**
         * js可以调用该类的方法,并给当前方法 传参
         */
        @JavascriptInterface
        public void showToast(String arg) {
            Toast.makeText(TcpClientActivity.this, arg, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public String back() {
            return "hello world";
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /**
         * <p>
         *    WebView 可返回其历史page
         * </p>
         * */
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    class MyDownloadLister implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            /**
             * <p>下载任务主要分两种：</p>
             * <ul>
             *     <li>自定义下载</li>
             *     <li>调用系统的download模块</li>
             * </ul>
             * */
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private OnClickListener l = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final String msg = mMessageEditText.getText().toString();
            if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
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

                mWebView.loadUrl("javascript:javaCalls(" +
                        "'" + mMessageEditText.getText().toString() + "'" + ")");
            }
        }
    };

}
