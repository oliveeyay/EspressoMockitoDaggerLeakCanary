package com.og.emdlc.application

import android.app.Application
import com.og.emdlc.dagger.AppComponent
import com.og.emdlc.dagger.AppModule
import com.og.emdlc.dagger.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary

/**
 * Created by olivier.goutay on 3/31/17.
 * Extends the [Application] to initialize frameworks / libraries at app start
 */
class EmdlcApplication : Application() {

    companion object {
        @JvmStatic lateinit var daggerComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerAppComponent.builder()
                .appModule(AppModule(false))
                .build()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }
}
