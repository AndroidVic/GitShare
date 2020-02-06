package net.promasoft.trawellmate;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import net.promasoft.trawellmate.argapp.UserDetailsArg;
import net.promasoft.trawellmate.db.SharedPrefHelper;
import net.promasoft.trawellmate.db.UserPrefHelper;
import net.promasoft.trawellmate.util.AlineActivityHelper;
import net.promasoft.trawellmate.util.DialogLogin;
import net.promasoft.trawellmate.util.ToastHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class StartPageAct extends AppCompatActivity {
    private static final int RC_SIGN_IN = 00001;
    Button call;
    private GoogleSignInClient mGoogleSignInClient;
    private DialogLogin dialogLogin;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_travel_modified);
        new AlineActivityHelper(StartPageAct.this, false);

        SharedPrefHelper.getInstance(StartPageAct.this).setIsFirstTime(false);

        ImageView bgImg = findViewById(R.id.Ida_start_bg);

        Button signIn = findViewById(R.id.ID_start_sgnin);
        signIn.setOnClickListener(view -> {
            dialogLogin = new DialogLogin(StartPageAct.this, false, new DialogLogin.OnLoginListner() {
                @Override
                public void onLoginSuccess() {
                    new ToastHelper(StartPageAct.this, "Welcome John").setDuration(1000).show();
                    SharedPrefHelper.getInstance(StartPageAct.this).setIsLogin(true);
                    startActivity(new Intent(StartPageAct.this, HomeAct.class));
                    finish();

                }

                @Override
                public void onSignUpClicked() {
                    startActivity(new Intent(StartPageAct.this, SignupActivity.class));

                }
            });
            dialogLogin.showPage();
        });


        Button signUp = findViewById(R.id.ID_start_sgnup);
        signUp.setOnClickListener(view -> {
            startActivity(new Intent(StartPageAct.this, SignupActivity.class));
        });


        Button skip = findViewById(R.id.ID_start_skip);
        skip.setOnClickListener(view -> {
            startActivity(new Intent(StartPageAct.this, HomeAct.class));
            finish();
        });


        ImageView cloud1 = findViewById(R.id.Ida_st_cloud1);
        ImageView cloud2 = findViewById(R.id.Ida_st_cloud2);
        ImageView cloud3 = findViewById(R.id.Ida_st_cloud3);
        ImageView cloud4 = findViewById(R.id.Ida_st_cloud4);
        ImageView cloud5 = findViewById(R.id.Ida_st_cloud5);
        ImageView cloud6 = findViewById(R.id.Ida_st_cloud6);

        bgImg.startAnimation(AnimationUtils.loadAnimation(StartPageAct.this, R.anim.floating_anim_x_lng));
//
        startAnim(cloud1, 100, R.anim.floating_anim_x);
        startAnim(cloud2, 200, R.anim.floating_anim_x_lng_rev);
        startAnim(cloud3, 300, R.anim.floating_anim_x_lng);

        startAnim(cloud4, 200, R.anim.floating_anim_xy_rev);
        startAnim(cloud5, 300, R.anim.floating_anim_x_lng_rev);
        startAnim(cloud6, 300, R.anim.floating_anim_x_lng);

        CardView loginGoogle = findViewById(R.id.ID_st_google);
        loginGoogle.setOnClickListener(view -> {
            signIn();
        });

        CardView loginFB = findViewById(R.id.ID_st_facebk);
        loginFB.setOnClickListener(view -> {
            fbLogin();
        });
        initAuthGoogle();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        createKeyHash(StartPageAct.this, getPackageName());
    }

    private void fbLogin() {
        //callbackManager = CallbackManager.Factory.create();

        callbackManager = CallbackManager.Factory.create();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            handleFacebookToken(accessToken);
//            LoginManager.getInstance().logOut();
        } else {

            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            handleFacebookToken(loginResult.getAccessToken());
                        }

                        @Override
                        public void onCancel() {
                            Toast.makeText(StartPageAct.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            Toast.makeText(StartPageAct.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        }
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email",  "user_birthday", "user_location"));
    }

    private void handleFacebookToken(AccessToken accessToken) {

//        Profile profile = Profile.getCurrentProfile();
//        if (profile != null) {
//            String f_name = profile.getFirstName();
//            String m_name = profile.getMiddleName();
//            String l_name = profile.getLastName();
//            String fullName = profile.getName();
//            String fb_image = profile.getProfilePictureUri(100, 100).toString();
//        } else {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    UserDetailsArg userDetailsArg = new UserDetailsArg();
                    String id = object.getString("id");
                    try {
                        URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                        userDetailsArg.userPhotUri = profile_pic.toString();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    if (object.has("name"))
                        userDetailsArg.userFullName = object.getString("name");
                    if (object.has("first_name"))
                        userDetailsArg.userFirstName = object.getString("first_name");
                    if (object.has("middle_name"))
                        userDetailsArg.userMiddleName = object.getString("middle_name");
                    if (object.has("last_name"))
                        userDetailsArg.userLastName = object.getString("last_name");
                    if (object.has("email"))
                        userDetailsArg.userEmail = object.getString("email");
                    if (object.has("gender"))
                        userDetailsArg.userGender = object.getString("gender");
                    if (object.has("birthday"))
                        userDetailsArg.userDOB = object.getString("birthday");
                    if (object.has("location"))
                        userDetailsArg.userLocation = object.getJSONObject("location").getString("name");

                    LoginManager.getInstance().logOut();

                    UserPrefHelper.getInstance(StartPageAct.this).setUserData(userDetailsArg);

                    Intent intent = new Intent(StartPageAct.this, MobileValidateAct.class);
                    intent.putExtra("name", userDetailsArg.userFirstName);
                    startActivity(intent);


                } catch (JSONException e) {
//                    Toast.makeText(StartPageAct.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, link, name, first_name, middle_name, last_name, email, gender, birthday, location"); // Parámetros que pedimos a facebook
        request.setParameters(parameters);
        request.executeAsync();
//        }
    }


    private void initAuthGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
    }

    private void signIn() {
        mGoogleSignInClient.signOut();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("StartPage", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    private void updateUI(GoogleSignInAccount account) {
//        Toast.makeText(this, "Name: " + account.getDisplayName(), Toast.LENGTH_SHORT).show();
        UserDetailsArg userDetailsArg = new UserDetailsArg();
        userDetailsArg.userFullName = account.getDisplayName();
        userDetailsArg.userFirstName = account.getGivenName();
        userDetailsArg.userLastName = account.getFamilyName();
        userDetailsArg.userEmail = account.getEmail();
        Uri photoUrl = account.getPhotoUrl();
        if (photoUrl != null) {
            try {
                userDetailsArg.userPhotUri = photoUrl.toString();
            } catch (Exception e) {

            }
        }

        UserPrefHelper.getInstance(StartPageAct.this).setUserData(userDetailsArg);

        Intent intent = new Intent(StartPageAct.this, MobileValidateAct.class);
        intent.putExtra("name", userDetailsArg.userFirstName);
        startActivity(intent);


//        Log.d("StartPage", "Account: " + account);
    }

    private void startAnim(final View view, int delay, final int anim) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    view.setVisibility(View.VISIBLE);
                    view.startAnimation(AnimationUtils.loadAnimation(StartPageAct.this, anim));

                } catch (Exception e) {

                }
//                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.flip_anim);
//                anim.setTarget(view);
//                anim.setDuration(800);
//                anim.start();
            }
        }, delay);
    }

    public static void createKeyHash(Activity activity, String yourPackage) {
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(yourPackage, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }//0wqSRCJj4Zn2m6LRjx3zpL8s/3E=
    }

}
