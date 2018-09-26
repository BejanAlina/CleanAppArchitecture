package ro.absolute.cleanapparchitecturedataflowdependency

import android.app.Application
import org.koin.android.ext.android.startKoin
import ro.absolute.cleanapparchitecturedataflowdependency.di.dataModule

/**
 * Created by Alina on 9/10/2018.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(dataModule))
    }
}