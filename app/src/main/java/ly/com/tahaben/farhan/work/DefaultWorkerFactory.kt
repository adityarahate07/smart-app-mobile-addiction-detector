package ly.com.tahaben.farhan.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 30,Apr,2023
 */
class DefaultWorkerFactory constructor(
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return null
    }
}
