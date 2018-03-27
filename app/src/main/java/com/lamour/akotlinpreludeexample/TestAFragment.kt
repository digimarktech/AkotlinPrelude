package com.lamour.akotlinpreludeexample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.lamour.akotlinprelude.IActivityMiddleman
import com.lamour.akotlinprelude.PlainFragment
import java.lang.ref.WeakReference

class TestAFragment: PlainFragment() {
    override var title: String? = null
    override var middlemanListener: WeakReference<IActivityMiddleman>? = null

    private var button: Button? = null



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_test_2, container,false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view?.findViewById(R.id.button21)

        button?.setText("Launch New Button")

        setFragmentTitle()

        button?.setOnClickListener {
            middlemanListener?.get()?.broadcasting(this, 21)
            middlemanListener?.get()?.shouldBeReplaced(this)
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        setMiddlemanListener(activity)
    }

    override fun onDetach() {
        super.onDetach()
        releaseMiddleListener()
    }
}