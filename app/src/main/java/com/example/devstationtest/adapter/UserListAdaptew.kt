package com.example.devstationtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.devstationtest.R
import com.example.devstationtest.interfaces.UserListItemClick
import com.example.devstationtest.model.Results
import com.squareup.picasso.Picasso

class UserListAdapter (private val dataList:List<Results>, private val itemClick: UserListItemClick):
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private lateinit var mContext: Context
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userName: AppCompatTextView = itemView.findViewById(R.id.userName)
        val userCountry: AppCompatTextView = itemView.findViewById(R.id.userCountry)
        val profileImage: AppCompatImageView = itemView.findViewById(R.id.profile_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val itemView =
            LayoutInflater.from(mContext).inflate(R.layout.user_list_item,parent,false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.userName.text = "${item.name?.title} ${item.name?.first} ${item.name?.last}"
        holder.userCountry.text = "${item.location?.country}"
        Picasso.get()
            .load(item.picture?.thumbnail)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(holder.profileImage)
        holder.itemView.setOnClickListener {
            itemClick.itemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
