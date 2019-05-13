package common;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import com.core.hilosdecolores.R;
import com.core.hilosdecolores.mainmodule.MainModule.MainMenu;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {
    private static final String DESCUENTO = "descuento";

    public FCMService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().size() > 00 && remoteMessage.getNotification() !=null){

            senndNotification(remoteMessage);
        }


    }

    private void senndNotification(RemoteMessage remoteMessage) {

            float desc = Float.valueOf(remoteMessage.getData().get(DESCUENTO));
        Intent intent = new Intent(this, MainMenu.class);
        intent.putExtra(DESCUENTO,desc);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.needle)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(getResources().getString(R.string.app_name))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getString(R.string.low_channel_id) ;
            String channelName =  getString(R.string.low_channel_name) ;


            NotificationChannel channel = new NotificationChannel(channelId, channelName,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 200, 50});
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }

            notificationBuilder.setChannelId(channelId);
        }

        if (notificationManager != null){
            notificationManager.notify("", 0, notificationBuilder.build());
        }


    }
}
