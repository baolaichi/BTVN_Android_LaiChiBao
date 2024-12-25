// MainActivity.java
package vn.edu.tnut.btvn_1;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private WebViewHandler webViewHandler;
    private Button switchButton;
    private Button checkButton;
    private EditText ageInput;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        switchButton = findViewById(R.id.switchButton);
        checkButton = findViewById(R.id.checkButton);
        ageInput = findViewById(R.id.ageInput);
        resultText = findViewById(R.id.resultText);

        // Khởi tạo WebViewHandler
        webViewHandler = new WebViewHandler(webView);
        webViewHandler.setupWebView(); // Cấu hình WebView

        // Set onClickListener cho nút chuyển sang WebView
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ẩn các thành phần giao diện liên quan đến thông tin cá nhân
                findViewById(R.id.infoTextView).setVisibility(View.GONE);
                ageInput.setVisibility(View.GONE);
                checkButton.setVisibility(View.GONE);
                resultText.setVisibility(View.GONE);

                // Hiển thị WebView
                webView.setVisibility(View.VISIBLE);
                webViewHandler.loadURL("file:///android_asset/index.html");
            }
        });

        // Set onClickListener cho nút kiểm tra độ tuổi
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageText = ageInput.getText().toString();
                if (!ageText.isEmpty()) {
                    try {
                        int age = Integer.parseInt(ageText);
                        if (age < 18) {
                            resultText.setText("Bạn đang học phổ thông.");
                        } else if (age <= 23) {
                            resultText.setText("Bạn đang học đại học.");
                        } else {
                            resultText.setText("Bạn đang đi làm.");
                        }
                    } catch (NumberFormatException e) {
                        resultText.setText("Vui lòng nhập số hợp lệ.");
                    }
                } else {
                    resultText.setText("Vui lòng nhập độ tuổi.");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.getVisibility() == View.VISIBLE) {
            // Ẩn WebView và hiển thị lại các phần giao diện khác
            webView.setVisibility(View.GONE);
            findViewById(R.id.infoTextView).setVisibility(View.VISIBLE);
            ageInput.setVisibility(View.VISIBLE);
            checkButton.setVisibility(View.VISIBLE);
            resultText.setVisibility(View.VISIBLE);
        } else if (webView.canGoBack()) {
            webView.goBack(); // Quay lại trang trước đó trong WebView
        } else {
            super.onBackPressed();
        }
    }
}
