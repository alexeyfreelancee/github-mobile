package com.example.githubmobile.user_profile

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubmobile.R
import com.example.githubmobile.data.models.events.UserEvent
import kotlinx.android.synthetic.main.user_event_row.view.*

class UserEventsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var activityList = ArrayList<UserEvent>()


    class UserActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: UserEvent) {
            itemView.event_repo_name.text = event.repo.name
            itemView.event_time.text = event.created_at
            itemView.event_type.text = event.type


            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.data = Uri.parse(event.repo.url)
                itemView.context.startActivity(intent)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserActivityViewHolder {
        return UserActivityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_event_row, parent, false)
        )
    }

    override fun getItemCount(): Int = activityList.size

    fun createList(newList: ArrayList<UserEvent>) {
        this.activityList = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserActivityViewHolder -> holder.bind(activityList[position])
        }
    }
}