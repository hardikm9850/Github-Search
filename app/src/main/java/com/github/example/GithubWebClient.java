package com.github.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardik on 25/12/17.
 */

public class WebClient extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView webview;

    public static final String TAG_URL = "github_url";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webclient);
        ButterKnife.bind(this);

    }

}
