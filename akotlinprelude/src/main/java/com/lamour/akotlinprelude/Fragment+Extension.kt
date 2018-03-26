package com.lamour.akotlinprelude

/**
 * Created by lamour on 3/25/18.
 * Fragment+Extension deals with how fragments are shown, replaced onto AppCompatActivity
 */

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity




/**
presentActivity: an activity from current one
@param toActivity
 */
fun AppCompatActivity.presentActivity(toActivity: Activity){
    //this.startActivity(Intent(this, toActivity::class.java))
}

fun AppCompatActivity.transitioning(toActivity: Activity) {
    //val intent = Intent(this,toActivity::class.java)
}

/**
 * presentFragment: would present a fragment on a given activity
    @param fragment
    @param tag: optional string
    @param containerId: integer identification of the fragment container
    @param shouldAddToBackStack: boolean value with a default value of false
 */
fun AppCompatActivity.presentFragment(fragment: Fragment, tag:String?, containerId:Int, shouldAdd: Boolean = false) {
    val transaction = supportFragmentManager
            .beginTransaction()
            .add(containerId,fragment,tag)

    when (shouldAdd) {
        true -> { transaction.addToBackStack(tag) }
        false -> { transaction.addToBackStack(null) }
    }

    transaction.commit()
}

/**
 * replaceFragment: would replace a fragment on a given activity
    @param fragment
    @param tag: optional string
    @param containerId: integer identification of the fragment container
    @param shouldAddToBackStack: boolean value with a default value of false
 */
fun AppCompatActivity.replaceFragment(fragment: Fragment, tag: String?, containerId: Int, shouldAdd: Boolean = false) {
    val transaction = supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment, tag)

    when (shouldAdd) {
        true -> { transaction.addToBackStack(tag) }
        false -> { transaction.addToBackStack(null) }
    }

    transaction.commit()
}

/**
 * replaceWith: would replace a fragment with another fragment
    @param fragment
    @param tag: optional string
    @param containerId: integer identification of the fragment container
    @param shouldAddToBackStack: boolean value with a default value of false
 */
fun Fragment.replaceWith(fragment: Fragment, tag: String?,containerId: Int, shouldAdd: Boolean = false) {
    var transaction = this.fragmentManager
            .beginTransaction()
            .replace(containerId,fragment)

    when(shouldAdd) {
        true -> { transaction.addToBackStack(tag) }
        false -> { transaction.addToBackStack(null)}
    }

    transaction.commit()
}
