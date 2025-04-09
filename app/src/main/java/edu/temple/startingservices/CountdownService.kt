package edu.temple.startingservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountdownService: Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Service State", "STARTED")
        val countdownValue = intent?.getIntExtra("countdown_value", 0) ?: 0

        coroutineScope.launch {
            startCountdown(countdownValue)
        }

        return START_NOT_STICKY
    }

    private suspend fun startCountdown(countdownValue: Int) {
        Log.d("Service State", "Coroutine Started")
        for (i in countdownValue downTo 0) {
            delay(1000)  // Wait for 1 second before continuing
            Log.d("Service State", "Countdown: $i seconds remaining")
        }
        Log.d("Service State", "Coroutine Finished")
        stopSelf()

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Service State", "STOPPED")
        coroutineScope.cancel()  // Cancel the Coroutine when service is destroyed

    }


}