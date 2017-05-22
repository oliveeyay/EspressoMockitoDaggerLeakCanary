package com.og.emdlc.dagger

import com.og.emdlc.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by olivier.goutay on 3/31/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    //Activities
    fun inject(mainActivity: MainActivity)

    //Tests
    fun inject(abstractTest: AbstractTest)
}
