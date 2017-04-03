package com.og.emdlc.dagger;

import com.og.emdlc.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by olivier.goutay on 3/31/17.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    //Activities
    void inject(MainActivity mainActivity);

    //Tests
    void inject(AbstractTest abstractTest);
}
