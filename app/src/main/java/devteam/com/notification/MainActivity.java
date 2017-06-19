package devteam.com.notification;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button botao = (Button) findViewById(R.id.botao);
        Button botaoCancelarNotificação = (Button) findViewById(R.id.botaoCancelarNotificação);

        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplicationContext(),Notification_reciever.class);

                /*CHECK IF ALERT HAS NOT BEEN CREATED*/
                boolean alarmeAtivo = (PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_NO_CREATE) == null);

                if(alarmeAtivo)
                {
                    Toast.makeText(getApplicationContext(),"ACTIVE ALARM!",Toast.LENGTH_SHORT).show();
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.add(Calendar.SECOND, 3);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    //RTC = SHOW WARNINGS EVEN IF CELL IS BLOCKED, RUNTIME, REPEAT TIME IN MILLIS, INTENT
                    //alarmManager.INTERVAL_DAY - 24h
                    //alarmManager.set(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent); - TO NOTIFICATION ONCE
                    alarmManager.setRepeating(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 5000,pendingIntent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"ALARM JA THIS ACTIVE!",Toast.LENGTH_SHORT).show();
                }
            }//onClick
        });


        botaoCancelarNotificação.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"CANCELED ALARM!",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getApplicationContext(),Notification_reciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();



        /*
         SUBMIT NOTIFICATION ONCE...
        Intent intent =new Intent(getApplicationContext(),Notification_reciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        */

    }
}
