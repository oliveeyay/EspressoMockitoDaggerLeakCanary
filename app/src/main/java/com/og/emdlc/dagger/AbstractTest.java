package com.og.emdlc.dagger;

import android.content.Context;

import com.og.emdlc.application.EmdlcApplication;
import com.og.emdlc.managers.AccountManager;

import javax.inject.Inject;

/**
 * Created by olivier.goutay on 1/25/17.
 */
public class AbstractTest {

    @Inject
    protected AccountManager mAccountManager;

    /**
     * Inject all the Managers/Dependencies you want to use in tests here
     */
    protected void beforeActivityLaunched(Context context) {
        ((EmdlcApplication) context.getApplicationContext()).getDaggerComponent().inject(this);
    }

    /**
     * Used to reset the injection
     */
    protected void tearDown(Context context) {
        ((EmdlcApplication) context.getApplicationContext()).setDaggerComponent(null);
    }
}
