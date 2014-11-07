/*
    Author: Kamal Chaya
    Incline Measuring application for Android

    The code to read the sensor values was adapted from here:
        capycoding.blogspot.com/2012/10/get-angle-from-sensor-in-android.html


 */

package cs496.KamalChaya.inclinemeasure.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends ActionBarActivity {

    //Variables to hold the angles for the three axes of rotation
    private float x = 0, y = 0, z = 0;

    /*
        array of floats to hold readings of accelerometer and
        magnetic field sensor
     */
    private float[] accelReadings, magReadings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


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
