package com.lamour.akotlinpreludeexample

import android.os.Bundle
import com.lamour.akotlinprelude.PlainFragment
import com.lamour.akotlinprelude.TabActivity.TabActivity

class TabBarActivity: TabActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPrimaryConfiguration()


        val fragmentA = TestFragment()
        fragmentA.title = "Fragment A"
        fragmentA.text = "We are in Fragment A"

        val fragmentB = TestAFragment()
        fragmentB.title = "Fragment B"

        val fragmentC = TestFragment()
        fragmentC.title = "Fragment C"
        fragmentC.text = "We are in Fragment C"

        var fragments = listOf<PlainFragment>(fragmentA,fragmentB,fragmentC)
        setFragments(fragments)

        compose()
    }

}