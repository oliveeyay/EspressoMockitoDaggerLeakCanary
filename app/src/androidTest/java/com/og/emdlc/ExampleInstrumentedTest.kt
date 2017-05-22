package com.og.emdlc

import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.widget.TextView
import com.og.emdlc.activities.MainActivity
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest : AbstractEspressoMockitoTest() {

    @Rule @JvmField
    var activityRule: IntentsTestRule<MainActivity> = object : IntentsTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            this@ExampleInstrumentedTest.beforeActivityLaunched()
        }
    }

    @Test
    @Throws(Exception::class)
    fun testEspressoMockitoDagger() {
        assertEquals("Mockito", (getView(R.id.activity_main_tv) as TextView).text.toString())
    }
}
