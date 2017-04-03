package com.og.emdlc.dagger;

import com.og.emdlc.managers.AccountManager;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by olivier.goutay on 3/31/17.
 * Think about the release AppModule (necessary for proguard to avoid mockito in release)
 */
@Module
public class AppModule {

    /**
     * The boolean used to return real instances of managers, or
     * {@link Mockito#mock(Class)} instances for test purpose.
     */
    private boolean mMockManagers;

    public AppModule(boolean mockManagers) {
        this.mMockManagers = mockManagers;
    }

    @Provides
    @Singleton
    AccountManager provideAccountManager() {
        if (mMockManagers) {
            return Mockito.mock(AccountManager.class);
        }
        return new AccountManager();
    }
}
