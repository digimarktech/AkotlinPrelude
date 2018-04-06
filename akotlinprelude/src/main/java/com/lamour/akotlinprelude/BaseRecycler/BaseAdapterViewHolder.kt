package com.lamour.akotlinprelude.BaseRecycler

import android.support.v7.widget.RecyclerView
import android.view.View

/*
* A Recycler is made of a ViewHolder (cell for each row)
* and Adapter that populates the recycler's  number of rows,
* and also supplies data for each ViewHolder
* */


open class BaseRecyclerViewHolder<A>(private val cellView: View):
        RecyclerView.ViewHolder(cellView) {
    /*
    * within configure method, you'll set your
    * subviews with data
    * */
    open fun configure(item: A) { }

}