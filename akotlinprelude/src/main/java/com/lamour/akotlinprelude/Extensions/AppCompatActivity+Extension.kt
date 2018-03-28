package com.lamour.akotlinprelude.Extensions

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.presentActivity(intendedActivity: Activity) {
    val intent = Intent(this, intendedActivity::class.java)
    startActivity(intent)
}

