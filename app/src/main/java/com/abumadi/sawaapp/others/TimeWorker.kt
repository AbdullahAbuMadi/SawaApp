package com.abumadi.sawaapp.others

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

private const val TAG = "TimeWorker"

class TimeWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        val number = inputData.getInt("number", 0)

        Log.d(TAG, "doWork: number:$number")
        for (i in number downTo 0) {
            Log.d(TAG, "doWork: i was $i")
            try {
                Thread.sleep(1000)
            } catch (e: Exception){
                e.printStackTrace()
                return Result.failure()
            }
        }
        //do what you want here
        return Result.success()
    }
}