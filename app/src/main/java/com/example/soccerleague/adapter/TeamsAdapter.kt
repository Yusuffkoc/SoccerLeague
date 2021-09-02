package com.example.soccerleague.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soccerleague.R
import com.example.soccerleague.model.TeamResponse

class TeamsAdapter : RecyclerView.Adapter<TeamsAdapter.MyViewHolder>() {

    var teamList = mutableListOf<TeamResponse>()
    var context: Context? = null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var teamName: TextView
        var teamLogo: ImageView

        init {
            teamName = itemView.findViewById(R.id.teamName)
            teamLogo = itemView.findViewById(R.id.teamLogo)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsAdapter.MyViewHolder {
        val contentView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_team, parent, false)

        return MyViewHolder(contentView)
    }

    override fun onBindViewHolder(holder: TeamsAdapter.MyViewHolder, position: Int) {
        val currentItem = teamList[position]
        holder.teamName.text = currentItem.strTeam
        Glide.with(context!!).load(currentItem.strTeamBadge).into(holder.teamLogo)

        //holder.idTextView.text = currentItem.id.toString()
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    fun setData(list: List<TeamResponse>, _context: Context) {
        teamList = list as MutableList<TeamResponse>
        this.context = _context
        notifyDataSetChanged()
    }
}