package com.og.emdlc.dagger

import com.og.emdlc.managers.AccountManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by olivier.goutay on 3/31/17.
 * Think about the release AppModule (necessary for proguard to avoid mockito in release)
 */
@Module
class AppModule(mockManagers: Boolean) {

    @Provides
    @Singleton
    internal fun provideAccountManager(): AccountManager {
        return AccountManager()
    }
}
