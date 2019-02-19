package com.satyaraj.app.testapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MessageListener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String messageBody = smsMessage.getMessageBody();
                String phoneNumber = smsMessage.getDisplayOriginatingAddress();

                sendNotification(context , phoneNumber , messageBody);
            }
        }
    }

    public void sendNotification(Context context, String phoneNumber, String messageBody  ) {

        //Get an instance of NotificationManager//


        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(context, MainActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context,"ok")
                        .setContentIntent(resultPendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(phoneNumber)
                        .setContentText( messageBody);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, mBuilder.build());



    }
}
