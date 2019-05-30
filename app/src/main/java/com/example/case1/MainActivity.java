package com.example.case1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.app.Notification.PRIORITY_HIGH;

public class MainActivity extends AppCompatActivity {

    Button Col, Ex ;
    int requestCode = 123;
    int notificationID = 888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ex = findViewById(R.id.btnEx);
        

        Ex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
                    channel.setDescription("This is for default notification");
                    notificationManager.createNotificationChannel(channel);
                }

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                NotificationCompat.InboxStyle iStyle =  new NotificationCompat.InboxStyle();
                iStyle.addLine("Mobile 50% off");
                iStyle.addLine("Laptop 20% off");
                iStyle.addLine("Tablet 22% off");
                iStyle.addLine("Printer 30% off");
                iStyle.addLine("Others 10% off");
                iStyle.setSummaryText("+2 more");


                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("Deals from top electronics retailers");
                builder.setContentText("Excellent deals");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pendingIntent);
                builder.setStyle(iStyle);
                builder.setAutoCancel(true);

                Notification n = builder.build();

                notificationManager.notify(notificationID, n);
                finish();
            }

        });

    }
}

