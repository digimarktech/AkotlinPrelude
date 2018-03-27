package com.lamour.akotlinprelude

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import java.lang.ref.WeakReference
import android.app.Activity


/*
Multi-Pane UI (manipulating various fragments onto a single Activity)

- each fragment should customize the activity to their like, i.e change activity's title
- passing data around from fragment to fragment using the activity as the middleman
- control back stack for fragment
*/


/*
* FragmentAction is enum of action on how
* we decide to manipulate our fragments within our
* Activity.
* */
public enum class FragmentAction {
    Present,
    Replace, // will remove all fragments into the back stack and add new fragment onto the stack's top.
    Remove
}

/*
* interactingWith allows to add, replace or remove a fragment within our activity
*
* @param fragment
* @param withTag: optional string
* @param forAction: type of FragmentAction, where you can either present, replace, or remove
* @param ontoContainerViewId: the frameLayout id
* @param shouldAddToStack: default false
* */
public fun AppCompatActivity.interactingWith(fragment: Fragment,
                                      withTag: String?,
                                      forAction: FragmentAction,
                                      ontoContainerViewId: Int,
                                      shouldAddToStack: Boolean = false) {

    val transactionManager = supportFragmentManager // get transactionManager from Activity
    val transaction = transactionManager.beginTransaction()

    when(forAction) {
        FragmentAction.Present -> { transaction.add(ontoContainerViewId,fragment,withTag) }
        FragmentAction.Replace -> { transaction.replace(ontoContainerViewId,fragment,withTag) }
        FragmentAction.Remove -> { transaction.remove(fragment) }
    }

    when (shouldAddToStack) { // wether we should add to back stack
        false       -> {}
        true    -> { transaction.addToBackStack(withTag) }
    }

    transaction.commit()
}

/*
* IActivityMiddleman is an interface that forces an Activity
* to become a middleman to host all fragments.
**/
public interface IActivityMiddleman {
    public fun setActivityTitle(string: String)

    /*
    * if a fragment wishes to transmit a single item to another fragment
    * then you'd raise this method fun <A>broadcast(owner: Fragment, item: A)
    *
    * for example says that a fragment wants to emit this value of 10
    * if (owner == fragmentB) { val value = item as Int }
    *
    * */
    public fun <A>broadcasting(owner: Fragment, item: A)
    /*
    * if a fragment wishes to transmit a list of items to another fragment
    * then you'd raise this method fun <A>broadcast(owner: Fragment, items: List<A>)
    * */
    public fun <A>broadcasting(owner: Fragment, items: List<A>)
    /*
    *  showBeReplaced should be raised anytime you're finished with the current fragment
    *  for example say that you have a loginFragment that contains a button,
    *  once it is pressed and upon successful login, you'd raise
    *  this delegate method and let the middleman activity that it (current fragment) should be replaced.
    * */
    public fun shouldBeReplaced(current: Fragment)
}

/*
* IPlainFragment is an interface that forces a regular Fragment
* to contain a middleman listener, which it'll use to customize
* its host Activity, and broadcasting other information.
**/
public interface IPlainFragment {
    public var title: String? // fragment desired title

    public var middlemanListener: WeakReference<IActivityMiddleman>?

    public fun setFragmentTitle() {
        title?.let { middlemanListener?.get()?.setActivityTitle(it) }

    }

    /*
    * setMiddlemanListener should be set within your fragment's onAttach lifecycle
    * */
    public fun setMiddlemanListener(currentActivity: Activity) {
        if (currentActivity is IActivityMiddleman) {
            middlemanListener = WeakReference(currentActivity)
        }
    }

    /*
     * releaseMiddleListener should be set within your fragment's onDetach lifecycle
     * */
    public fun releaseMiddleListener() {
        middlemanListener = null
    }

}


/*
*  PlainFragment, an abstract class, that conforms both IPlainFragment
*  and Fragment (from support.v4)
* */
public abstract class PlainFragment: Fragment(), IPlainFragment {}


/*
*  PlainActivity, an abstract class, that conforms both IActivityMiddle
*  and AppCompatActivity.
*
*  It contains a FrameLayout to host your fragments, so don't forget to
*  call setFrameLayoutContainer within onCreate of your derived Activity
* */
public abstract class PlainActivity: AppCompatActivity(), IActivityMiddleman {
    public val containerViewId = R.id.plainActivityFrameLayoutId

    public fun setFrameLayoutContainer() {
        setContentView(R.layout.activity_plain)
    }
}