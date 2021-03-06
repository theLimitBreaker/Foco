package com.pervysage.thelimitbreaker.foco.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.pervysage.thelimitbreaker.foco.R
import com.pervysage.thelimitbreaker.foco.actvities.MainActivity
import com.pervysage.thelimitbreaker.foco.actvities.RejectedCallersActivity


const val MESSAGE_NOTIFY_ID=2
const val MESSAGE_NOTIFY_KEY="message_notify_key"
const val GROUP_KEY = "foco_message_group"

fun sendGeofenceNotification(notifyMsg: String, transitionType: Int, context: Context) {

    val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Android O requires a Notification Channel.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.app_name)
        // Create the channel for the notification
        val mChannel = NotificationChannel("channel_id", name, NotificationManager.IMPORTANCE_DEFAULT)
        // Set the Notification Channel for the Notification Manager.
        mNotificationManager.createNotificationChannel(mChannel)
    }

    // Create an explicit content Intent that starts the main Activity.
    val notificationIntent = Intent(context.applicationContext, MainActivity::class.java)

    // Construct a task stack.
    val stackBuilder = TaskStackBuilder.create(context)

    // Add the main Activity to the task stack as the parent.
    stackBuilder.addParentStack(MainActivity::class.java)

    // Push the content Intent onto the stack.
    stackBuilder.addNextIntent(notificationIntent)

    // Get a PendingIntent containing the entire back stack.
    val notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

    // Get a notification builder that's compatible with platform versions >= 4
    val builder = NotificationCompat.Builder(context)

    // Define the notification settings.
    builder.setSmallIcon(R.drawable.ic_notification)
            // In a real app, you may want to use a library like Volley
            // to decode the Bitmap.
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,
                    R.drawable.ic_notification))
            .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            .setColorized(true)
            .setGroup(GROUP_KEY)
            .setGroupSummary(true)
            .setContentIntent(notificationPendingIntent)

    // Set the Channel ID for Android O.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        builder.setChannelId("channel_id") // Channel ID
    }
    when (transitionType) {
        Geofence.GEOFENCE_TRANSITION_ENTER -> builder.setOngoing(true)
                .setContentTitle(notifyMsg)
                .setContentText("foco at your service")
        Geofence.GEOFENCE_TRANSITION_EXIT -> builder.setAutoCancel(true)
                .setContentTitle(notifyMsg)
                .setContentText("Service finished for now")
        else -> builder.setAutoCancel(true)
                .setContentTitle("Service Stopped")
                .setContentText(notifyMsg)
    }

    // Issue the notification
    mNotificationManager.notify(0, builder.build())
}


fun sendDriveModeNotification(notifyMsg: String, contentText: String, hasEntered:Boolean, context: Context) {

    val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Android O requires a Notification Channel.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.app_name)
        // Create the channel for the notification
        val mChannel = NotificationChannel("channel_id", name, NotificationManager.IMPORTANCE_DEFAULT)
        // Set the Notification Channel for the Notification Manager.
        mNotificationManager.createNotificationChannel(mChannel)
    }

    // Create an explicit content Intent that starts the main Activity.
    val notificationIntent = Intent(context.applicationContext, MainActivity::class.java)

    // Construct a task stack.
    val stackBuilder = TaskStackBuilder.create(context)

    // Add the main Activity to the task stack as the parent.
    stackBuilder.addParentStack(MainActivity::class.java)

    // Push the content Intent onto the stack.
    stackBuilder.addNextIntent(notificationIntent)

    // Get a PendingIntent containing the entire back stack.
    val notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

    // Get a notification builder that's compatible with platform versions >= 4
    val builder = NotificationCompat.Builder(context)

    // Define the notification settings.
    builder.setSmallIcon(R.drawable.ic_notification)
            // In a real app, you may want to use a library like Volley
            // to decode the Bitmap.
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,
                    R.drawable.ic_notification))
            .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            .setColorized(true)
            .setGroup(GROUP_KEY)
            .setGroupSummary(true)
            .setContentIntent(notificationPendingIntent)

    // Set the Channel ID for Android O.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        builder.setChannelId("channel_id") // Channel ID
    }

    builder.setContentTitle(notifyMsg)
            .setContentText(contentText)
    if (hasEntered)
        builder.setOngoing(true)
    else
        builder.setAutoCancel(true)

    // Issue the notification
    mNotificationManager.notify(1, builder.build())
}

fun sendRejectCallerNotification(context: Context,smsStatus:Boolean) {

    val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Android O requires a Notification Channel.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.app_name)
        // Create the channel for the notification
        val mChannel = NotificationChannel("channel_id_sms", name, NotificationManager.IMPORTANCE_DEFAULT)
        // Set the Notification Channel for the Notification Manager.
        mNotificationManager.createNotificationChannel(mChannel)
    }

    // Create an explicit content Intent that starts the main Activity.
    val notificationIntent = Intent(context.applicationContext, RejectedCallersActivity::class.java)

    // Construct a task stack.
    val stackBuilder = TaskStackBuilder.create(context)

    // Add the main Activity to the task stack as the parent.
    stackBuilder.addParentStack(MainActivity::class.java)

    // Push the content Intent onto the stack.
    stackBuilder.addNextIntent(notificationIntent)

    // Get a PendingIntent containing the entire back stack.
    val notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

    // Get a notification builder that's compatible with platform versions >= 4
    val builder = NotificationCompat.Builder(context)

    // Define the notification settings.
    builder.setSmallIcon(R.drawable.ic_notification)
            // In a real app, you may want to use a library like Volley
            // to decode the Bitmap.
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,
                    R.drawable.ic_notification))
            .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            .setColorized(true)
            .setContentTitle("${if (smsStatus) "Message sent to rejected callers" else "Calls rejected by foco on your behalf"}")
            .setContentText("Tap to see the callers")
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)

    // Set the Channel ID for Android O.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        builder.setChannelId("channel_id_sms") // Channel ID
    }

    // Issue the notification
    mNotificationManager.notify(MESSAGE_NOTIFY_ID, builder.build())
}