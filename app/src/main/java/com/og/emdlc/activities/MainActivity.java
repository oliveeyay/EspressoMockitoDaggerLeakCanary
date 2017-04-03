package com.og.emdlc.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.og.emdlc.R;
import com.og.emdlc.application.EmdlcApplication;
import com.og.emdlc.managers.AccountManager;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    protected AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Injection
        ((EmdlcApplication) getApplicationContext()).getDaggerComponent().inject(this);

        //TextView
        ((TextView) findViewById(R.id.activity_main_tv)).setText(mAccountManager.getAccountFirstName());
    }
}
