package com.lamour.akotlinpreludeexample


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.lamour.akotlinprelude.Extensions.presentActivity


class MainActivity: AppCompatActivity() {
    lateinit var multiPlaneButton: Button
    lateinit var tabBarActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_example)

        multiPlaneButton = findViewById(R.id.MultiPlaneExampleId)
        tabBarActivityButton = findViewById(R.id.tabBarActivityExampleId)


        multiPlaneButton.setOnClickListener {
            presentActivity(MultiPlane())
        }

        tabBarActivityButton.setOnClickListener {
            presentActivity(TabBarActivity())
        }
    }


}




