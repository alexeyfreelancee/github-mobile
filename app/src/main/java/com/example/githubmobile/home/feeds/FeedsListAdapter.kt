package com.example.githubmobile.home.feeds

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubmobile.R
import com.example.githubmobile.utils.getStringDate
import com.sun.syndication.feed.synd.SyndEntry
import com.sun.syndication.feed.synd.SyndFeed
import kotlinx.android.synthetic.main.feed_row.view.*

class FeedsListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = ArrayList<SyndEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.feed_row, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: SyndEntry){
            itemView.apply {

                setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(item.link)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    itemView.context.startActivity(intent)
                }

                text.text = item.title
                time.text = item.publishedDate.getStringDate()

            }
        }
    }

    fun createNewFeed(newList: ArrayList<SyndEntry>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}