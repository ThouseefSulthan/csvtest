package in.presencesoft.csvtest.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;


import java.util.concurrent.TimeUnit;

import in.presencesoft.csvtest.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CommonClass {
    private Context context;

    public CommonClass(Context con) {
        this.context = con;
    }

    public boolean isOnline() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            for (NetworkInfo anInfo : info) {
                if (anInfo.getState() == NetworkInfo.State.CONNECTED) {

                    return true;
                    //isInternetAvailable();
                }
            }
        }
        return false;
    }



    public void showAlertForInternet() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(context.getResources().getString(R.string.alert));
        alertDialog.setMessage(context.getResources().getString(R.string.internet_alert));
        alertDialog.setPositiveButton(context.getResources().getString(R.string._settings), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intent);
            }
        });
        alertDialog.show();
    }

    public void showAlertWithTitle(String title, String strMsg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setCancelable(false);
        alertDialog.setTitle(title);
        alertDialog.setMessage(strMsg);

        alertDialog.setNegativeButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }


    public String oKHttpGet(String url) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();


        Log.e("GetURL", url);

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();

            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
