package com.raju.rigi_sample.application

import android.app.Application
import android.os.StrictMode
import com.raju.rigi_sample.preference.PreferenceProvider
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.notifications.handler.NotificationConfig
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.pushprovider.firebase.FirebasePushDeviceGenerator

class RigiApp : Application() {

    private var client: ChatClient? = null
    private var pref: PreferenceProvider? = null
    val logTag = "##Application"
    override fun onCreate() {
        super.onCreate()
        initializeChatClient()
        setThreadPolicy()
        initPref()

    }

    private fun initPref() {
       pref = PreferenceProvider(applicationContext)
    }


    private fun setThreadPolicy() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

//    private fun initFirebaseRegistrationToken() {
//
//        FirebaseMessaging.getInstance().token
//            .addOnCompleteListener(object : OnCompleteListener<String?> {
//                override fun onComplete(task: Task<String?>) {
//                    if (!task.isSuccessful) {
//                        Log.w("Raju", "Fetching FCM registration token failed", task.exception)
//                        return
//                    }
//                    // Get new FCM registration token
//                    val token: String? = task.result
////                    PreferenceProvider(context!!).saveFCMToken(token)
//                    Log.d("Raju", token.toString())
//                }
//            })
//
//    }

    private fun initializeChatClient() {
        val notificationConfig = NotificationConfig(
            pushDeviceGenerators = listOf(FirebasePushDeviceGenerator())
        )

//        client = ChatClient.Builder("4jfaxywpq98t" ,applicationContext)
        client = ChatClient.Builder("b67pax5b2wdq" ,applicationContext)
//            .baseUrl("https://chat.stream-io-api.com/")
            .notifications(notificationConfig)
            .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
            .build()
        ChatDomain.Builder(client!!, applicationContext).build()
    }

    fun getChatClient(): ChatClient? {
        return client
    }

    fun getPref(): PreferenceProvider? {
        return pref
    }
}