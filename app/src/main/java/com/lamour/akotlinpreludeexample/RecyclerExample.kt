package com.lamour.akotlinpreludeexample

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.lamour.akotlinprelude.BaseRecycler.BaseRecyclerAdapter
import com.lamour.akotlinprelude.BaseRecycler.BaseRecyclerViewHolder


data class Post(val title:String, val body: String)

class PostRecyclerViewHolder(val view: View):
        BaseRecyclerViewHolder<Post>(view) {

    private var titleTextView: TextView = view.findViewById(R.id.titleTextView)
    private var bodyTextView: TextView = view.findViewById(R.id.bodyTextView)

    override fun configure(item: Post) {
        titleTextView.text = item.title
        bodyTextView.text = item.body
        super.configure(item)
    }
}

class PostRecyclerAdapter(private val posts: List<Post>)
    : BaseRecyclerAdapter<Post, PostRecyclerViewHolder>(posts) {

    override fun build(view: View): PostRecyclerViewHolder? {
        return PostRecyclerViewHolder(view)
    }

    override fun setLayoutReferenceId(): Int {
        return R.layout.post_cell
    }
}



fun buildRecycler(activity: Activity, recycler: RecyclerView?, items: List<Post>)  {
    recycler?.adapter = PostRecyclerAdapter(items)
    recycler?.layoutManager = LinearLayoutManager(activity)
}

