package lalinw.washington.edu.quizdroid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.List;

/**
 * Created by IreneW on 2017-03-01.
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getStringExtra("url");
        if (!url.equalsIgnoreCase("")) {
            Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
        }
    }


}