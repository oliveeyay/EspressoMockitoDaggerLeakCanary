package com.og.emdlc.application;

import android.app.Application;

import com.og.emdlc.dagger.AppComponent;
import com.og.emdlc.dagger.AppModule;
import com.og.emdlc.dagger.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by olivier.goutay on 3/31/17.
 * Extends the {@link Application} to initialize frameworks / libraries at app start
 */
public class EmdlcApplication extends Application {

    /**
     * The {@link AppComponent}
     */
    private AppComponent mDaggerComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * Allows to inject dependencies into classes
     */
    public AppComponent getDaggerComponent() {
        if (mDaggerComponent == null) {
            mDaggerComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(false))
                    .build();
        }
        return mDaggerComponent;
    }

    /**
     * For test purpose.
     */
    public void setDaggerComponent(AppComponent daggerComponent) {
        mDaggerComponent = daggerComponent;
    }
}
