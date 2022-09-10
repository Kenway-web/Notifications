package com.example.notifcationdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val channelID="com.example.notifcationdemo.channel1"

    // defining notification manager instance , require for notification instance

    private var notificationManager:NotificationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID,"DemmoChannel","this is a demo ")
        button.setOnClickListener {
            displayNotification()
        }

    }

    //diplays a icon title and description
    private fun displayNotification()
    {
        val notificationId=45
        val notification=NotificationCompat.Builder(this@MainActivity,channelID)
                        .setContentTitle("Demo Title")
                        .setContentText("This is a demo notification")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .build()
        notificationManager?.notify(notificationId,notification)

    }

    private fun createNotificationChannel(id:String, name:String, channelDescription: String){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            val importance=NotificationManager.IMPORTANCE_HIGH
            val channel= NotificationChannel(id,name,importance).apply {
                description=channelDescription
            }
            notificationManager?.createNotificationChannel(channel)

        }

    }


}