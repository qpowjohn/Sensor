package taiwan.beginner.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager sm = null;
    TextView xViewA = null;
    TextView yViewA = null;
    TextView zViewA = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xViewA = (TextView) findViewById(R.id.xbox);
        yViewA = (TextView) findViewById(R.id.ybox);
        zViewA = (TextView) findViewById(R.id.zbox);
        //取得sensor service
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //註冊Listener(SensorEventListener,sensor的感測類型,適合的sensor採樣變化率)
        sm.registerListener(acc_listener, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_NORMAL);



    }
    private SensorEventListener acc_listener = new SensorEventListener() {
        //當sensor的準確性改變時會執行
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            Log.d("Sensor_test","onAccuracyChanged: " + sensor + ", accuracy: " + accuracy);
        }

        //sensor座標變動時執行
        @Override
        public void onSensorChanged(SensorEvent event) {
            xViewA.setText("ORIENTATION_X: " + event.values[1]);
            yViewA.setText("ORIENTATION_Y: " + event.values[2]);
            zViewA.setText("ORIENTATION_Z: " + event.values[0]);
            /*
             * value[0]：Z軸，Sensor方位，北：0、東：90、南：180、西：270
             * value[1]：X軸，Sensor傾斜度(抬起手機頂部，X軸的值會變動)
             * value[2]：Y軸，Sensor滾動角度(側邊翻轉)
             */
        }
    };

}
