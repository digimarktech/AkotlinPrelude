package com.lamour.akotlinpreludeexample

import MVPGeneric.BaseView
import MVPGeneric.BasePresenter
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lamour.akotlinprelude.presentFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // basic mvp architecture/ design for a login checkout mvpExample.kt  
      // val loginFragment =  composeLogin(this) //<--- this would compose your loginfragment
    }


}
