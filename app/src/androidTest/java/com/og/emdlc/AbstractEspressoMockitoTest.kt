package com.og.emdlc

import android.app.Activity
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.support.test.runner.lifecycle.Stage.RESUMED
import android.view.View
import com.og.emdlc.application.EmdlcApplication
import com.og.emdlc.dagger.AbstractTest
import com.og.emdlc.dagger.AppModule
import com.og.emdlc.dagger.DaggerAppComponent

/**
 * Created by olivier.goutay on 3/31/17.
 */

open class AbstractEspressoMockitoTest : AbstractTest() {

    /**
     * Need to execute this in the [IntentsTestRule.beforeActivityLaunched] to mock [DaggerAppComponent] dependencies
     */
    protected fun beforeActivityLaunched() {
        val context = getInstrumentation().targetContext
        EmdlcApplication.daggerComponent = DaggerAppComponent.builder()
                .appModule(AppModule(true))
                .build()

        super.beforeActivityLaunched(context)
    }

    /**
     * Allows to retrieve a [View] for the [.getCurrentActivity]

     * @param viewId The id we want to retrieve
     */
    protected fun getView(viewId: Int): View? {
        return currentActivity?.findViewById(viewId)
    }

    /**
     * Returns the current [Stage.RESUMED] [Activity]
     * Waits for [.waitForIdleSync] before executing.

     * @return The current [Activity]
     */
    private val currentActivity: Activity?
        get() {
            waitForIdleSync()
            val activity = arrayOfNulls<Activity>(1)
            getInstrumentation().runOnMainSync {
                var currentActivity: Activity? = null
                val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED)
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity = resumedActivities.iterator().next() as Activity
                    activity[0] = currentActivity
                }
            }

            return activity[0]
        }

    /**
     * Wait for the UI and Tasks to be idle
     */
    private fun waitForIdleSync() {
        getInstrumentation().waitForIdleSync()
    }

}
