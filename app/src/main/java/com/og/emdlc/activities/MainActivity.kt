package com.og.emdlc.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.og.emdlc.R
import com.og.emdlc.application.EmdlcApplication
import com.og.emdlc.managers.AccountManager

import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        //Injection
        EmdlcApplication.daggerComponent.inject(this)

        //TextView
        (findViewById(R.id.activity_main_tv) as TextView).text = accountManager.accountFirstName()
    }
}
