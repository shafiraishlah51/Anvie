package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.core.graphics.drawable.IconCompat;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(context, "Internet is running by using Mobile data", Toast.LENGTH_SHORT).show();
            }
            if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                Toast.makeText(context, "Internet is running by using WIFI data", Toast.LENGTH_SHORT).show();
            }
        }
        else{
                Toast.makeText(context, "Internet is not running", Toast.LENGTH_SHORT).show();
        }
    }
}
