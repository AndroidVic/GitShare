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

import com.google.gson.Gson;

import net.promasoft.trawellmate.R;
import net.promasoft.trawellmate.args.LoginArgs;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginReqsHandler {

    private static final int BUFFER_SIZE = 1024;
    private static final String SERVICE_URL = "https://trawellmate.com/api/v1/users/";

    public static Drawable getDrawable(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(id, context.getTheme());
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    public static boolean isNetworkConnected(Context context) {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception ex) {
        }
        return connected;
    }

    public static String getBaseUrl(String method) {
        StringBuilder requestUrl = new StringBuilder(LoginReqsHandler.SERVICE_URL);
        if (method != null && method.trim().length() > 0) {
            requestUrl.append("" + method.trim());
        }

        return requestUrl.toString();
    }

    private static void request(final Context context, final String method, final String args, final Handler callback) {
//        try {
//            if (ApiKey == null || UserID == null) {
//                ApiKey = SharedPrefHelper.getInstance(context).getUserApi();
//                UserID = SharedPrefHelper.getInstance(context).getUserId();
//            }
//        } catch (Exception e) {
//
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = "";
                try {
                    if (LoginReqsHandler.isNetworkConnected(context)) {
                        URL url = new URL(getBaseUrl(method));
                        if (url != null) {
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            if (connection != null) {
                                connection.setReadTimeout(20 * 1000);
                                connection.setConnectTimeout(20 * 1000);
                                if (args != null) {
                                    connection.setDoOutput(true);
                                    connection.setRequestMethod("POST");
//                                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                    connection.setRequestProperty("charset", "utf-8");
                                } else {
                                    connection.setDoOutput(false);
                                    connection.setRequestMethod("GET");
                                }
                                connection.connect();
                                /*----- WRITE INPUT INTO SERVER -----*/
                                if (args != null) {
                                    if (args != null && args.trim().length() > 0) {
                                        OutputStreamWriter out_sw = new OutputStreamWriter(connection.getOutputStream());
                                        out_sw.write(args);
                                        out_sw.flush();
                                        out_sw.close();
                                    }
                                }

                                /*----- READ OUTPUT FROM SERVER -----*/
                                InputStream in = connection.getInputStream();
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                try {
                                    byte[] buffer = new byte[LoginReqsHandler.BUFFER_SIZE];
                                    int read = in.read(buffer);
                                    while (read != -1) {
                                        out.write(buffer, 0, read);
                                        read = in.read(buffer);
                                    }
                                    json = out.toString();

//                                    Log.d("JSONWRITE",json.toString());
                                } catch (Exception ex) {
                                } finally {
                                    try {
                                        if (in != null) {
                                            in.close();
                                        }
                                    } catch (Exception ex) {
                                    }
                                    try {
                                        if (out != null) {
                                            out.close();
                                        }
                                    } catch (Exception ex) {
                                    }
                                }
                            }
                        }
                    } else {
                        json = context.getString(R.string.no_connection);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, context.getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                } catch (Exception ex) {
                    Log.e("Service", "exception : " + ex.getMessage());
                    try {
                        json = ex.getMessage();
                    } catch (Exception e) {
                    }
                } finally {
                    if (callback != null) {
                        String finalJson = json;
                        //Not passing any token for validation
                        callback.sendMessage(callback.obtainMessage(0, json));
                    }
                }
            }
        }).start();
    }

    public static void getLogin(String userName, String password, Context context, Handler callback) {
        StringBuilder params = new StringBuilder();
        params.append("user_name=" + userName);
        params.append("&user_password=" + password);
        request(context, "", params.toString(), callback);
    }


    public static void checkMobileNumber(Context context, String mobile, Handler callback) {
        StringBuilder params = new StringBuilder();
        params.append("mobile_number=" + mobile);
        request(context, "mobile_stat", params.toString(), callback);
    }


}
