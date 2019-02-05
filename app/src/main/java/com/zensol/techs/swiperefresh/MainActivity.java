package com.zensol.techs.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    WebView webView;
    WebViewClient webViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeRefreshLayout = findViewById(R.id.SwipeRefresh_id);
        webView = findViewById(R.id.WebView_id);

        webView.loadUrl("https://www.google.com");

        // refresh............
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

        //
        webViewClient = new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // when load page then stop
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);

                swipeRefreshLayout.setColorSchemeResources(R.color.colorFinishRefresh);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

                swipeRefreshLayout.setColorSchemeResources(R.color.colorLoadRefresh);
            }
        };

        webView.setWebViewClient(webViewClient);
    }
}
