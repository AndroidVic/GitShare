package net.promasoft.trawellmate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.promasoft.trawellmate.util.AlineActivityHelper;

public class FaqAct extends AppCompatActivity {

    private WebView webView;
    private ProgressBar mLoadingProgress;

    Runnable exit = new Runnable() {
        @Override
        public void run() {
            CAN_EXIT = false;
        }
    };
    Handler handler = new Handler();
    private boolean CAN_EXIT;
    private AlertDialog alertDialog;
    private AlertDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        new AlineActivityHelper(FaqAct.this, true);


//        progressBar = new ProgressDialog(MainActivity.this);

        progressBar = CreateProgress(FaqAct.this,"Loading...");
        progressBar.setMessage("Loading...");
        progressBar.show();

        alertDialog = new AlertDialog.Builder(this).create();

        mLoadingProgress = (ProgressBar) findViewById(R.id.ID_ProgressBar);
        mLoadingProgress.setMax(100);

        webView = (WebView) findViewById(R.id.ID_web_view);
        webView.setWebViewClient(new MyBrowser() {

        });
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        webView.loadUrl("http://trawellmate.com/contact");
        webView.loadUrl("http://trawellmate.com/faq");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                mLoadingProgress.setProgress(progress);
                // Return the app name after finish loading
                if (progress >= 100) {

                }

            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            mLoadingProgress.setVisibility(View.VISIBLE);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mLoadingProgress.setVisibility(View.INVISIBLE);
            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {


            alertDialog.setTitle("Error");
            alertDialog.setMessage(description);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
            alertDialog.show();
        }

    }

    private  AlertDialog CreateProgress(Context context, String message) {
        final ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);

        progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        progressBar.setIndeterminate(true);
        final LinearLayout container = new LinearLayout(context);
        container.addView(progressBar);
        int padding = 100;
        container.setPadding(padding - 50, (message == null ? padding : 0), padding - 50, 0);

         AlertDialog.Builder builder =
                new  AlertDialog.Builder(context).
                        setMessage(message).
                        setView(container);
        return builder.create();
    }
    @Override
    public void onBackPressed() {
        if (!webView.canGoBack()) {
            checkExit();
            return;
        }
        if (webView.canGoBackOrForward(-2)) {
            progressBar.setMessage("Please wait...");
            progressBar.show();
            webView.goBack();
        } else {
            WebBackForwardList mWebBackForwardList = webView.copyBackForwardList();
            String historyUrl = mWebBackForwardList.getItemAtIndex(1).getUrl();
            if (historyUrl.contains("dashboard")) {
                checkExit();
            } else {
                if (webView.canGoBack()) {
                    progressBar.setMessage("Please wait...");
                    progressBar.show();
                    webView.goBack();
                }
            }
        }
    }

    private void checkExit() {
        super.onBackPressed();
//        if (!CAN_EXIT) {
//            Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
//            CAN_EXIT = true;
//            try {
//                handler.postDelayed(exit, 1500);
//            } catch (Exception e) {
//
//            }
//        } else {
//            try {
//                handler.removeCallbacks(exit);
//            } catch (Exception e) {
//
//            }
//            super.onBackPressed();
//        }
    }
}
