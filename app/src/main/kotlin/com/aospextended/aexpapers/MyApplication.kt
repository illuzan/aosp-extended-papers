package com.aospextended.aexpapers

import dev.jahir.frames.ui.FramesApplication
import com.onesignal.OneSignal
import com.onesignal.OSNotificationReceivedEvent
import dev.jahir.frames.extensions.context.preferences

class MyApplication : FramesApplication() {
    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this);
        OneSignal.setAppId(BuildConfig.ONESIGNAL_APP_ID);

        OneSignal.setNotificationWillShowInForegroundHandler { notificationReceivedEvent: OSNotificationReceivedEvent ->
            notificationReceivedEvent.complete(
                if (preferences.notificationsEnabled)
                    notificationReceivedEvent.notification
                else null
            )
        }

        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        OneSignal.pauseInAppMessages(true)
        OneSignal.setLocationShared(false)
    }
}
