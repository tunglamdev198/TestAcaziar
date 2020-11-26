package com.lamnt.testacaziasoft.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.lamnt.testacaziasoft.R
import com.lamnt.testacaziasoft.models.User
import com.lamnt.testacaziasoft.utils.Utils
import kotlinx.android.synthetic.main.item_card_user.view.*

class UserCardAdapter constructor(private val onLogoutClick: OnLogoutClick)
    : RecyclerView.Adapter<UserCardAdapter.ViewHolder>() {

    private var items: ArrayList<User> = ArrayList()
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_card_user, parent, false)
        return ViewHolder(view,onLogoutClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<User>) {
        val currentSize = items.size
        this.items.addAll(items)
        notifyItemRangeChanged(currentSize-1,items.size-1)
    }

    fun getItems() : ArrayList<User> = items

    class ViewHolder(itemView: View, private val onLogoutClick: OnLogoutClick) :
        RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(user: User){
            val imgUrl = user.picture.large
            Glide.with(itemView.context).load(imgUrl).into(itemView.imgAvatar)
            itemView.txtLabel.text = "My Name is"
            itemView.txtDetail.text = user.name.first + " " + user.name.last
            itemView.tabSelect.setOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let { displayInfo(it,user) }
                }

            })
        }

        @SuppressLint("SetTextI18n")
        private fun displayInfo(position: Int, user: User) {
            when(position){
                0 ->{
                    itemView.txtLabel.text = "My Name is"
                    itemView.txtDetail.text = user.name.first + " " + user.name.last
                }

                1 ->{
                    itemView.txtLabel.text = "My Dob is"
                    itemView.txtDetail.text = Utils.formatData(user.dob.date)
                }

                2 ->{
                    itemView.txtLabel.text = "My Address is"
                    itemView.txtDetail.text = user.location.state + " - " + user.location.city
                }

                3 ->{
                    itemView.txtLabel.text = "My Phone is"
                    itemView.txtDetail.text = user.phone
                }

                4 ->{
                    onLogoutClick.onLogout()
                }
            }
        }
    }

    interface OnLogoutClick {
        fun onLogout()
    }
}