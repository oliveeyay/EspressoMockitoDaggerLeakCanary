package com.og.emdlc.dagger

import android.content.Context

import com.og.emdlc.application.EmdlcApplication
import com.og.emdlc.managers.AccountManager

import javax.inject.Inject

/**
 * Created by olivier.goutay on 1/25/17.
 */
open class AbstractTest {

    @Inject
    lateinit var accountManager: AccountManager

    /**
     * Inject all the Managers/Dependencies you want to use in tests here
     */
    protected fun beforeActivityLaunched(context: Context) {
        EmdlcApplication.daggerComponent.inject(this)
    }

}
