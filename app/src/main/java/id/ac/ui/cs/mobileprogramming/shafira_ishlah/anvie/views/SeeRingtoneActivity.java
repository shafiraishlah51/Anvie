package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.opengl.GLSurfaceView;
import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;

public class SeeRingtoneActivity extends Activity implements View.OnClickListener {

    //button objects
    private Button buttonStart;
    private Button buttonStop;
    private MusicView musicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_ringtone);

        //getting buttons from xml
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        //attaching onclicklistener to buttons
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        musicView =(MusicView) findViewById(R.id.musicView);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonStart) {
            //starting service
            if (isConnected()) {
                Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();
                startService(new Intent(this, MyService.class));
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection, Please activate your internet!", Toast.LENGTH_SHORT).show();
            }
        } else if (view == buttonStop) {
            //stopping service
            stopService(new Intent(this, MyService.class));
        }
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicView.onPause();
    }
}

