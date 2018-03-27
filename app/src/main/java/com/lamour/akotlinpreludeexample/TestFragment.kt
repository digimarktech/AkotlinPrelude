package com.lamour.akotlinpreludeexample

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.lamour.akotlinprelude.IActivityMiddleman
import com.lamour.akotlinprelude.PlainFragment
import java.lang.ref.WeakReference

class TestFragment: PlainFragment() {
    override var title: String? = null
    override var middlemanListener: WeakReference<IActivityMiddleman>? = null

    private var button: Button? = null
    private var textView: TextView? = null
    var text:String? = null



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_test, container,false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button = view?.findViewById(R.id.button1)
        textView = view?.findViewById(R.id.textView1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView?.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }

        button?.setText("Launch New")

        setFragmentTitle()

        text?.let { textView?.text = it }

        button?.setOnClickListener {
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