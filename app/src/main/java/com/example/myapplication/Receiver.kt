package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Create the notification when the broadcast is received
        createNotification(context)
    }

    private fun createNotification(context: Context) {
        // Create the notification channel for Android O and above
        val channelId = "cake_order_channel"
        val notificationId = 1

        // Define the NotificationManager
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Cake Order Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Create an Intent that opens the app when the notification is clicked
        val contentIntent = Intent(context, MainActivityMy::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Build the notification
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification) // Ensure this icon is added to res/drawable
            .setContentTitle("Order Successful!")
            .setContentText("You have successfully ordered. We will provide your cake soon!")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // Dismiss notification when clicked
            .build()

        // Notify the user
        notificationManager.notify(notificationId, notification)
    }
}