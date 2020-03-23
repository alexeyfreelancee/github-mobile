package com.example.githubmobile.github_repos

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubmobile.R
import com.example.githubmobile.data.models.github_repository.GithubRepo
import kotlinx.android.synthetic.main.github_repos_row.view.*

class GithubReposListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var reposList: ArrayList<GithubRepo> = ArrayList()


    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repository: GithubRepo) {
            itemView.repo_description.text = repository.description
            Glide.with(itemView.context)
                .load(repository.owner.avatar_url)
                .into(itemView.repo_image)
            itemView.repo_lang.text = repository.language
            itemView.repo_stars.text = repository.stargazers_count.toString()
            itemView.repo_title.text = repository.name
            itemView.repo_username.text = repository.owner.login

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(repository.html_url)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.github_repos_row, parent, false)
        )
    }

    override fun getItemCount(): Int = reposList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepositoryViewHolder -> {
                holder.bind(reposList[position])
            }
        }
    }

    fun createList(newList: ArrayList<GithubRepo>) {
        this.reposList = newList
        notifyDataSetChanged()
    }
}