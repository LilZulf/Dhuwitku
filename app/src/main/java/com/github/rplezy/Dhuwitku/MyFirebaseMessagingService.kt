package com.github.rplezy.Dhuwitku

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class MyFirebaseMessagingService : FirebaseMessagingService(){
    private val TAG = "FirebaseToken"
    private lateinit var notificationManager: NotificationManager
    private val ADMIN_CHANNEL_ID = "Dhuwitku"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i(TAG, token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.let {
            Log.i(TAG, it.data.get("message"))
        }

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
//            setupNotificationChannel()
        }

        val notificationId = Random.nextInt(60000)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Transfer Topup")
            .setContentText("Transfer saldo dari user lain telah berhasil")
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId,notificationBuilder.build())


        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG,"Message data payload : "+remoteMessage.data)
            if (remoteMessage.data.isNullOrEmpty()){
                val msg = remoteMessage.data.get("message").toString()
            }
        }



    }

    private fun setupNotificationChannel() {
//        val adminChannelName = getString(R.string.notifications_admin_channel_name)
//        val adminChannelDescription = getString(R.string.notifications_admin_channel_description)
//
//        val adminChannel : NotificationChannel
//        adminChannel = NotificationChan nel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_LOW)

    }

}