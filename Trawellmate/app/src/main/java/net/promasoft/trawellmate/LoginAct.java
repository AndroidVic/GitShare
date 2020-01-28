package net.promasoft.trawellmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import net.promasoft.trawellmate.dsgn.CustomTextLink;
import net.promasoft.trawellmate.util.LoginReqsHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginAct extends AppCompatActivity {

    private LinearLayout infoTextLay;
    private TextView signinText;
    private CardView loginCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        getWindow().getDecorView().setSystemUiVisibility(flag);
//        Window window = getWindow();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
        setContentView(R.layout.activity_login);


        loginCard = findViewById(R.id.Ida_login_card);
        loginCard.setVisibility(View.GONE);
        infoTextLay = findViewById(R.id.Ida_info_text);
        infoTextLay.setVisibility(View.VISIBLE);
        signinText = findViewById(R.id.Ida_signintext);

        final TextView text1 = findViewById(R.id.Ida_text1);
        final TextView text2 = findViewById(R.id.Ida_text2);
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        startAnim(text1, 100, R.anim.fade_in_anim);
        startAnim(text2, 500, R.anim.fade_in_anim);


        final CardView usernameCard = findViewById(R.id.IDa_username);
        usernameCard.setVisibility(View.INVISIBLE);
        final CardView passwordCard = findViewById(R.id.Ida_password);
        passwordCard.setVisibility(View.INVISIBLE);
        final Button loginBt = findViewById(R.id.ID_login_signin);
        loginBt.setVisibility(View.INVISIBLE);
        final CustomTextLink textLink = findViewById(R.id.ID_login_forget_pass);
        textLink.setVisibility(View.INVISIBLE);
        final TextView signinwithText = findViewById(R.id.Ida_signn_withtext);
        signinwithText.setVisibility(View.INVISIBLE);
        final LinearLayout signinwithCard = findViewById(R.id.Ida_signn_with);
        signinwithCard.setVisibility(View.INVISIBLE);


        Button continueBt = findViewById(R.id.ID_continue_bt);
        continueBt.setVisibility(View.INVISIBLE);
        startAnim(continueBt, 1400, R.anim.item_animation_fall_down);

        Button browseBt = findViewById(R.id.ID_browse_bt);
        browseBt.setVisibility(View.INVISIBLE);
        startAnim(browseBt, 1450, R.anim.item_animation_fall_down);

        browseBt.setOnClickListener(view -> {
            startActivity(new Intent(LoginAct.this, HomeAct.class));
        });

        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoTextLay.setVisibility(View.GONE);
                infoTextLay.startAnimation(AnimationUtils.loadAnimation(LoginAct.this, R.anim.fade_out_anim));
//                loginCard.startAnimation(AnimationUtils.loadAnimation(LoginAct.this, R.anim.fade_in_anim));
//                signinText.startAnimation(AnimationUtils.loadAnimation(LoginAct.this, R.anim.fade_in_anim));
//                loginCard.setVisibility(View.VISIBLE);
//                signinText.setVisibility(View.VISIBLE);

                startAnim(signinText, 100, R.anim.fade_in_anim);
                startAnim(loginCard, 120, R.anim.slide_up_fast);
                startAnim(usernameCard, 200, R.anim.slide_up);
                startAnim(passwordCard, 250, R.anim.slide_up);
                startAnim(loginBt, 300, R.anim.slide_up);
                startAnim(textLink, 350, R.anim.slide_up);
                startAnim(signinwithText, 400, R.anim.slide_up);
                startAnim(signinwithCard, 450, R.anim.slide_up);

            }
        });

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                initVolley();
//                try {
//                    LoginReqsHandler.getLogin("karthik@gmail.com", "karthik", LoginAct.this, new Handler(new Handler.Callback() {
//                        @Override
//                        public boolean handleMessage(Message msg) {
////                    Log.d("LoginAct", "" + msg);
//                            try {
//                                if (msg != null && msg.obj != null) {
//
//                                    String resp = (String) msg.obj;
//                                    Toast.makeText(LoginAct.this, "" + resp, Toast.LENGTH_SHORT).show();
//
//                                }
//                            } catch (Exception ex) {
//                                Log.e("LoginReceived", "error " + ex);
//                            } finally {
////                        loadingScreen.cancelLoading(null);
//
//                            }
//
//                            return false;
//                        }
//                    }));
//                } catch (Exception ex) {
//
//                }

                startActivity(new Intent(LoginAct.this, HomeAct.class));
            }
        });

        CustomTextLink signUpText = findViewById(R.id.ID_signup_text);
        signUpText.setOnClickListener(view -> {
            startActivity(new Intent(LoginAct.this, SignupActivity.class));
        });

        CustomTextLink frgtPassEmailSubmit = findViewById(R.id.ID_login_forget_pass);
        frgtPassEmailSubmit.setOnClickListener(view -> {
            startActivity(new Intent(LoginAct.this, ForgotPAsswordAct.class));
        });


    }

    private void initVolley() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.1.46:8000/api/login";
// prepare the Request
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "error");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", "karthik@gmail.com");
                params.put("password", "karthik");

                return params;
            }
        };
        queue.add(postRequest);

    }

    private void startAnim(final View view, int delay, final int anim) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    view.setVisibility(View.VISIBLE);
                    view.startAnimation(AnimationUtils.loadAnimation(LoginAct.this, anim));

                } catch (Exception e) {

                }
//                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_anim);
//                anim.setTarget(view);
//                anim.setDuration(800);
//                anim.start();
            }
        }, delay);
    }

}
