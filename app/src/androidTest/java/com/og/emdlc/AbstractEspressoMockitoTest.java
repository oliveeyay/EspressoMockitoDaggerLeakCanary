package com.og.emdlc;

import android.app.Activity;
import android.content.Context;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.view.View;

import com.og.emdlc.application.EmdlcApplication;
import com.og.emdlc.dagger.AbstractTest;
import com.og.emdlc.dagger.AppComponent;
import com.og.emdlc.dagger.AppModule;
import com.og.emdlc.dagger.DaggerAppComponent;

import org.junit.After;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.runner.lifecycle.Stage.RESUMED;

/**
 * Created by olivier.goutay on 3/31/17.
 */

public class AbstractEspressoMockitoTest extends AbstractTest {

    /**
     * Need to execute this in the {@link IntentsTestRule#beforeActivityLaunched()} to mock {@link DaggerAppComponent} dependencies
     */
    protected void beforeActivityLaunched() {
        Context context = getInstrumentation().getTargetContext();
        AppComponent mockDaggerComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(true))
                .build();
        ((EmdlcApplication) context.getApplicationContext()).setDaggerComponent(mockDaggerComponent);

        super.beforeActivityLaunched(context);
    }

    @After
    public void tearDown() {
        super.tearDown(getInstrumentation().getTargetContext());
    }

    /**
     * Allows to retrieve a {@link View} for the {@link #getCurrentActivity()}
     *
     * @param viewId The id we want to retrieve
     */
    protected View getView(int viewId) {
        return getCurrentActivity().findViewById(viewId);
    }

    /**
     * Returns the current {@link Stage#RESUMED} {@link Activity}
     * Waits for {@link #waitForIdleSync()} before executing.
     *
     * @return The current {@link Activity}
     */
    private Activity getCurrentActivity() {
        waitForIdleSync();
        final Activity[] activity = new Activity[1];
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Activity currentActivity = null;
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity = (Activity) resumedActivities.iterator().next();
                    activity[0] = currentActivity;
                }
            }
        });

        return activity[0];
    }

    /**
     * Wait for the UI and Tasks to be idle
     */
    private void waitForIdleSync() {
        getInstrumentation().waitForIdleSync();
    }

}
