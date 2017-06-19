package devteam.com.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class BrodcastReceiver_reboot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("RETURN", "ACTIVE ALARM");


        Intent notification = new Intent(context,Notification_reciever.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,notification,PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 3);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        //RTC = SHOW WARNINGS EVEN IF CELL IS BLOCKED, RUNTIME, REPEAT TIME IN MILLIS, INTENT
        //alarmManager.INTERVAL_DAY - 24h
        //alarmManager.set(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent); - TO NOTIFICATION ONCE
        alarmManager.setRepeating(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 5000,pendingIntent);
    }//onReceive
}//class BrodcastReceiver_reboot
