package net.promasoft.trawellmate.ntwk;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.args.LoginArgs;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginReqsVly {

    final String SERVICE_URL = "https://trawellmate.com/api/v1/users/";

    public boolean isNetworkConnected(Context context) {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception ex) {
        }
        return connected;
    }

    public String getBaseUrl(String method) {
        StringBuilder requestUrl = new StringBuilder(SERVICE_URL);
        if (method != null && method.trim().length() > 0) {
            requestUrl.append("" + method.trim());
        }

        return requestUrl.toString();
    }

    private void request(final Context context, final String method, final Map<String, String> params, VolleyCallback volleyCallback) {
        if (!isNetworkConnected(context)) {
            volleyCallback.onIntrnError("No Internet Connection");
            return;
        }

        final String url = getBaseUrl(method);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        if (volleyCallback != null) {
                            volleyCallback.onSuccess(response);
                        }
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        if (volleyCallback != null) {
                            volleyCallback.onRequestError(error);
                        }
                        Log.d("Error.Response", ""+error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        VolleyController.getInstance(context).add(postRequest);
    }

    public void getLogin(String userName, String password, Context context, VolleyCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", "karthik@gmail.com");
        params.put("password", "karthik");
        request(context, "", params, callback);
    }

    public void checkMobileDuplication(Context context, String mobile, VolleyCallback callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile_number", mobile);
        request(context, "mobile_stat", params, callback);
    }

    public interface VolleyCallback {

        void onSuccess(String result);

        void onRequestError(VolleyError errorMessage);

        void onIntrnError(String errorMessage);

    }

}
