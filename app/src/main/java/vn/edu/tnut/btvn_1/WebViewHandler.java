package vn.edu.tnut.btvn_1;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewHandler {

    private WebView webView;

    public WebViewHandler(WebView webView) {
        this.webView = webView;
    }

    public void setupWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false); // Cho phép video tự động phát

        webView.setWebChromeClient(new WebChromeClient()); // Đảm bảo video có thể chạy
        webView.setWebViewClient(new WebViewClient());
    }

    public void loadURL(String url) {
        webView.loadUrl(url); // Load file HTML
    }
}
