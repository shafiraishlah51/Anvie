package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.opengl.GLSurfaceView;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.R;

public class SeeRingtoneActivity extends Activity implements View.OnClickListener, SensorEventListener {

    //button objects
    private Button buttonStart;
    private Button buttonStop;
    private MusicView musicView;
    private OpenGLView openGLView;
    private TextView outPut1;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private final static int OVERFLOW_LIMIT = 20;
    private final static int SENSITIVITY = 4;
    private float[][] movingAverage = new float[3][OVERFLOW_LIMIT];
    private int overflow = 0;


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

        openGLView = findViewById(R.id.openGLView);
        outPut1 = findViewById(R.id.accText);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        initListeners();

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
        openGLView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicView.onPause();
        openGLView.onPause();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void initListeners()
    {
        mSensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener((SensorEventListener) this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (openGLView.GLReady) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float v1 = Math.round(event.values[0] * 100.0) / 100f;
                float v2 = Math.round(event.values[1] * 100.0) / 100f;
                float v3 = Math.round(event.values[2] * 100.0) / 100f;

                movingAverage[0][overflow] = v1;
                movingAverage[1][overflow] = v2;

                float s1 = calculateAverage(movingAverage[0]);
                float s2 = calculateAverage(movingAverage[1]);

                String eventValueString = String.format("a:%f b:%f c:%f", v1, v2, v3);
                outPut1.setText("accelerometer " + eventValueString);
                if (openGLView.renderer.objectsReady) {
                    openGLView.renderer.getCircle().CalculatePoints(s1 / SENSITIVITY, s2 / SENSITIVITY, 0.1f, 55);
                    openGLView.requestRender();
                }
            }
            openGLView.requestRender();
        }
        overflow += 1;
        if (overflow >= OVERFLOW_LIMIT) {
            overflow = 0;
        }
    }

    private float calculateAverage(float[] input) {
        DoubleStream io = IntStream.range(0, input.length)
                .mapToDouble(i -> input[i]);
        float sum = (float)io.sum();
        return sum/OVERFLOW_LIMIT;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

