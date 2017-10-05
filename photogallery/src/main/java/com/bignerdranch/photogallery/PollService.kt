package com.bignerdranch.photogallery

import android.app.AlarmManager
import android.app.IntentService
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.SystemClock
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log

/**
 * Created by hongy_000 on 2017/10/4.
 */
class PollService(var name: String) : IntentService(name) {

    constructor() : this(name = "PollService")

    private val TAG: String = PollService::class.java.simpleName

    init {
        name = TAG
    }

    companion object {
        private const val POLL_INTERNAL :Long = AlarmManager.INTERVAL_FIFTEEN_MINUTES
        /**
         * start this service
         * */
        fun newIntent(context: Context): Intent {
            return Intent(context, PollService::class.java)
        }

        /**
         * 延迟运行服务，更新数据
         * */
        fun setServiceAlarm(context: Context, isOn: Boolean) {
            val i: Intent = PollService.newIntent(context)
            val pi: PendingIntent = PendingIntent.getService(context, 0, i, 0)

            val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            if (isOn) {
                alarmManager.setInexactRepeating(
                        AlarmManager.ELAPSED_REALTIME,
                        SystemClock.elapsedRealtime(),
                        POLL_INTERNAL.toLong(), pi)
            } else {
                alarmManager.cancel(pi)
                pi.cancel()
            }
        }

        /**
         * 定时器是否在运行
         * */
        fun isServiceAlarmOn(context: Context): Boolean {
            val i: Intent = PollService.newIntent(context)
            return PendingIntent.getService(context,0, i, PendingIntent.FLAG_NO_CREATE) != null
        }
    }

    /**
     * 在工作线程执行，所有的耗时操作在此处处理
     * */
    override fun onHandleIntent(intent: Intent?) {
        if (!isNetworkAvailableAndConnected()) {
            return
        }
        Log.i(TAG, "Received an intent: " + intent)
        val query: String? = QueryPreferences.getStoredQuery(this)
        val lastResultId :String? = QueryPreferences.getLastResultId(this)
        val items :MutableList<GalleryItem>?

        items = if (null == query) {
            FlickrFetchrOkHttp().fetchRecentPhotos()
        } else {
            FlickrFetchrOkHttp().searchPhotos(query)
        }
        if (items.size == 0) {
            return
        }
        val resultId: String = items[0].id
        if (resultId == lastResultId) {
            Log.i(TAG, "Got a old result: " + resultId)
        } else {
            Log.i(TAG, "Got a new result: " + resultId)

            val resources: Resources = resources
            val i: Intent = PhotoGalleryActivity.newIntent(this)
            val pi: PendingIntent = PendingIntent.getService(this, 0, i, 0)

            val notification :Notification = NotificationCompat.Builder(this)
                    .setTicker(resources.getString(R.string.new_pictures_title))
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContentText(resources.getString(R.string.new_pictures_title))
                    .setContentTitle(resources.getString(R.string.new_pictures_text))
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build()
            val notificationManager :NotificationManagerCompat = NotificationManagerCompat.from(this)
            notificationManager.notify(0, notification)
        }
        QueryPreferences.setLastResultId(this, resultId)
    }

    private fun isNetworkAvailableAndConnected(): Boolean {
        val cm:ConnectivityManager  =  getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val isNetworkAvailable = cm.activeNetworkInfo != null
        return isNetworkAvailable && cm.activeNetworkInfo.isConnected
    }
}