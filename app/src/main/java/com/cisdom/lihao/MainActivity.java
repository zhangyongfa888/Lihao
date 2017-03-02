package com.cisdom.lihao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private WebView webview;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text_TextView);
        webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient() {
            /**
             * @param view
             * @param url
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;

            }
        });
        webview.loadUrl("file:///android_asset/index.html");

    }

    /**
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean
    onKeyDown(int
                      keyCode,
              KeyEvent event) {
        if (keyCode
                == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();//返回上个页面
            return
                    true;
        }
        return
                super.onKeyDown(keyCode,
                        event);//退出整个应用程序
    }

    private void getText1() {
//    for(int i=1;i++;i<=n){
//
//    }
    }

    /**
     *
     */
    private void getText() {
//        RequestParams params = new RequestParams("http://139.196.110.242:80/tsapi/index.php/ts_goods/getGoodsInfo");
//        params.addBodyParameter("good_id","GI0000000000036");
//       x.http().post(params, new Callback.CommonCallback<String>() {
//            /**
//             * @param result
//             */
//            @Override
//            public void onSuccess(String result) {
//                Log.i("result",result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//            }
//
//            @Override
//            public void onCancelled(Callback.CancelledException cex) {
//            }
//
//            @Override
//            public void onFinished() {
//            }
//        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://139.196.110.242:80/tsapi/index.php/ts_goods/getGoodsInfo",
                new Response.Listener<String>() {
                    /**
                     * @param response
                     */
                    @Override
                    public void onResponse(String response) {
                        Log.d("", "response -> " + response);

                        webview.loadDataWithBaseURL(null, response, "text/html", "UTF-8", null);
                    }
                }, new Response.ErrorListener() {
            /**
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("", error.getMessage(), error);
            }
        }) {
            /**
             * @return
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                Map<String, String> map = new HashMap<String, String>();
                map.put("good_id", "GI0000000000036");
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}
