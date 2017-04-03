package com.og.emdlc.dagger;

import com.og.emdlc.managers.AccountManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by olivier.goutay on 3/31/17.
 * Think about the release AppModule (necessary for proguard to avoid mockito in release)
 */
@Module
public class AppModule {

    public AppModule(boolean mockManagers) {
    }

    @Provides
    @Singleton
    AccountManager provideAccountManager() {
        return new AccountManager();
    }
}
