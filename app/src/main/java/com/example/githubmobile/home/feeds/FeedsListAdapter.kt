package com.example.githubmobile.home.feeds

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubmobile.R
import com.example.githubmobile.data.models.feed.Feed
import kotlinx.android.synthetic.main.feed_row.view.*

class FeedsListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Feed>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.feed_row, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(items[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: Feed){
            itemView.apply {
                //TODO
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(item.toString())
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    itemView.context.startActivity(intent)
                }
                text.text = item.toString()
                time.text = item.toString()
                Glide.with(context)
                    .load(item.toString())
                    .into(icon)
            }
        }
    }

    fun createNewList(newList: ArrayList<Feed>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}