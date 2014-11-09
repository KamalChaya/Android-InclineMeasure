/*
    Author: Kamal Chaya
    Incline Measuring application for Android

    The code to read the sensor values was adapted from here:
        capycoding.blogspot.com/2012/10/get-angle-from-sensor-in-android.html
        http://www.codingforandroid.com/2011/01/using-orientation-sensors-simple.html

 */

package cs496.KamalChaya.inclinemeasure.app;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.Math;

public class MainActivity extends ActionBarActivity implements SensorEventListener {

    //Variables to hold the angles for the three axes of rotation
    private float x = 0, y = 0, z = 0;

    /*
        array of floats to hold readings of accelerometer and
        magnetic field sensor
     */
    private float[] accelReadings, magReadings;

    /*
        Declaring variables for the sensor manager, the
        accelerometer, and the magnetic field sensor.
     */
    private SensorManager mSensorManager;
    Sensor accelerometer, magnetometer;

    /*
        Textviews to show the measured angles
     */
    TextView xValue, yValue, zValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize sensor capabilities and get the acceleromter and magnetic field sensor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        //Textviews which show the values
        xValue = (TextView) findViewById(R.id.pitchValue);
        yValue = (TextView) findViewById(R.id.rollValue);
        zValue = (TextView) findViewById(R.id.azimuthValue);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onResume();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            accelReadings = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            magReadings = event.values;
        if (magReadings != null && accelReadings != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, accelReadings, magReadings);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                z = (float) Math.toDegrees(orientation[0]);
                x = (float) Math.toDegrees(orientation[1]);
                y = (float) Math.toDegrees(orientation[2]);

                xValue.setText(Float.toString(x));
                yValue.setText(Float.toString(y));
                zValue.setText(Float.toString(z));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
