package devteam.com.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;

public class Notification_reciever  extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        /*DATE*/
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - H:m:s");
        SimpleDateFormat id = new SimpleDateFormat("Hms");
        String dateString = sdf.format(date);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context,Repeating_activity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //NOTIFICATION
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)//intent
                .setContentTitle("TITLE HERE - "+dateString)//TITLE
                .setContentText("DESCRIPTION HERE - "+dateString)//TEXT
                .setSmallIcon(R.mipmap.ic_launcher)//ICON
                .setAutoCancel(true);

        //EACH ID WILL GENERATE A DIFFERENT NOTIFICATION
        int idDinamico =   Integer.parseInt(id.format(date));
        //Fire the notification
        notificationManager.notify(idDinamico,builder.build());
    }
}
