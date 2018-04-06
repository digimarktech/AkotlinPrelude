package com.lamour.akotlinprelude.BaseRecycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


open class BaseRecyclerAdapter<A,
        V: BaseRecyclerViewHolder<A>>(private val items: List<A>)
        : RecyclerView.Adapter<V>() {

    var hasClickedOnRowAt:((Int, View?) -> Unit)? = null

    /*
    * setLayoutReferenceId would be the viewHolder fragment
    * and it needs to be override by its derived object
    *
    * override fun setLayoutReferenceId(): Int {
    *   return R.layout.someCell
    * }
    * */
    open fun setLayoutReferenceId() : Int {
        return 0
    }

    /*
    * As you know in onCreateViewHolder that returns your CustomViewHolder class,
    * since we are using Generic V that is a subclass of BaseRecyclerViewHolder<Something>
    * we won't be able to initialize V with the inflated view, thus we use this build method
    * and it should be override within your derived Adpater of BaseRecyclerAdapter
    *
    * it should return your customViewHolder i.e
    *
    * override fun build(view: View): CustomViewHolder? {
    *  return CustomViewHolder(view)
    * }
    * */
    open fun build(view: View) : V? {
        return null
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): V {
        val inflater = LayoutInflater.from(parent?.context)
        var view = inflater.inflate(setLayoutReferenceId(),parent, false)
        return build(view)!!
    }


    override fun getItemCount(): Int {
        return this.items.size
    }

    override  fun onBindViewHolder(holder: V?, position: Int) {
        holder?.configure(this.items[position])

        // listen for click onto cellView
        holder?.itemView?.setOnClickListener {
            hasClickedOnRowAt?.invoke(position, it)
        }
    }
}

