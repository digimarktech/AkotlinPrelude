package com.lamour.akotlinpreludeexample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.FrameLayout

import com.lamour.akotlinprelude.*


class MainActivity: PlainActivity() {

    private lateinit var frameLayout: FrameLayout
    private lateinit var fragmentA: TestFragment
    private lateinit var fragmentB: TestAFragment
    private lateinit var fragmentC: TestFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFrameLayoutContainer()
        frameLayout = findViewById<FrameLayout>(containerViewId)

        fragmentA = TestFragment()
        fragmentA.title = "Fragment A"
        fragmentA.text = "We are in Fragment A"
        interactingWith(fragmentA,"fragmentA",FragmentAction.Present,containerViewId)

    }



    override fun setActivityTitle(string: String) {
        Log.i("title", string)
        supportActionBar?.title = string
    }

    override fun <A> broadcasting(owner: Fragment, item: A) {
            if (owner == fragmentB)
                Log.i("fragmentB broad", (item as Int).toString())
    }

    override fun <A> broadcasting(owner: Fragment, items: List<A>) {

    }

    override fun shouldBeReplaced(current: Fragment) {
        if (current == fragmentA) {
            fragmentB = TestAFragment()
            fragmentB.title = "Fragment B"
            interactingWith(fragmentB,"fragmentB",FragmentAction.Replace,containerViewId)

        }

        if (current == fragmentB) {
            fragmentC = TestFragment()
            fragmentC.title = "Fragment C"
            fragmentC.text = "We are in Fragment C"
            interactingWith(fragmentC,"fragmentC",FragmentAction.Replace,containerViewId)
        }
    }

}


