package com.example.githubmobile.home.issues

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubmobile.R
import com.example.githubmobile.data.models.issue.Issue
import com.example.githubmobile.utils.getDate
import com.example.githubmobile.utils.log
import kotlinx.android.synthetic.main.issue_row.view.*
import java.lang.Exception

class IssuesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Issue>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.issue_row, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(items[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(issue: Issue) {
            try {
                itemView.apply {
                    title.text = issue.title
                    repo_name.text =
                        issue.repositoryUrl.replace("https://api.github.com/repos/".toRegex(), "")
                    time.text = issue.createdAt.getDate()
                    setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(issue.htmlUrl)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        itemView.context.startActivity(intent)
                    }
                }
            } catch (ex: Exception){}



        }
    }


    fun createNewList(newList: ArrayList<Issue>) {
        log("new list created ${newList.size}")
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}