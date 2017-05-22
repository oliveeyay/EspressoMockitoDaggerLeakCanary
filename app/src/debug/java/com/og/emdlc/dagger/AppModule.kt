package com.og.emdlc.dagger

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.og.emdlc.managers.AccountManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by olivier.goutay on 3/31/17.
 * Think about the release AppModule (necessary for proguard to avoid mockito in release)
 */
@Module
class AppModule(val mockManagers: Boolean) {

    @Provides
    @Singleton
    fun provideAccountManager(): AccountManager {
        if (mockManagers) {
            return mock {
                on { accountFirstName() } doReturn "Mockito"
            }
        }
        return AccountManager()
    }
}
