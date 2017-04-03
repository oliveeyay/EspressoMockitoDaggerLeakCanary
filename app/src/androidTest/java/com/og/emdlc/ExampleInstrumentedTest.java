package com.og.emdlc;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.og.emdlc.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest extends AbstractEspressoMockitoTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<MainActivity>(MainActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            ExampleInstrumentedTest.this.beforeActivityLaunched();

            //Mocking AccountManager#getAccountFirstName()
            when(mAccountManager.getAccountFirstName()).thenReturn("Mockito");
        }
    };

    @Test
    public void testEspressoMockitoDagger() throws Exception {
        assertEquals("Mockito", ((TextView) getView(R.id.activity_main_tv)).getText().toString());
    }
}
