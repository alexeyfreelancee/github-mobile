package com.example.githubmobile.home.pull_requests

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubmobile.R
import com.example.githubmobile.data.models.pull_request.PullRequest
import com.example.githubmobile.utils.parseDate
import kotlinx.android.synthetic.main.pull_request_row.view.*

class PullRequestsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<PullRequest>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return ViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.pull_request_row, parent, false)
       )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      when(holder){
          is ViewHolder -> holder.bind(items[position])
      }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: PullRequest){
            itemView.apply {
                repo_name.text = item.url.replace("https://api.github.com/repos/".toRegex(), "")
                title.text = item.title
                time.text = item.createdAt.parseDate()
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(item.htmlUrl)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun createNewList(newList: ArrayList<PullRequest>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}