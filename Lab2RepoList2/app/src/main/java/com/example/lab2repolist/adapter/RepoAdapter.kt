package com.example.lab2repolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2repolist.Repository
import com.example.lab2repolist.databinding.RepoItemLayoutBinding

class RepoAdapter(var items : List<Repository> = emptyList()) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = RepoItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.binding.repo = items[position];
    }

    inner class ViewHolder(val binding: RepoItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){}


}

