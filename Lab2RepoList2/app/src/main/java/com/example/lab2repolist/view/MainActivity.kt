package com.example.lab2repolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2repolist.adapter.RepoAdapter
import com.example.lab2repolist.databinding.RepoViewBinding
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    private val viewModel: RepositoryViewModel by inject()
    private lateinit var binding : RepoViewBinding
    private fun start(){
        startKoin{
            modules(koinModules)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        start()
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.repo_view)
        binding.viewModel = viewModel;


        val  button = findViewById<Button>(R.id.btnGetUserRepos);
        val repoView = findViewById<RecyclerView>(R.id.rvRepoView);

        val adapter = RepoAdapter();
        repoView.adapter = adapter;
        repoView.layoutManager = LinearLayoutManager(this);



        viewModel.userRepositories.observe(this, {
            run {
                adapter.items = it
                adapter.notifyDataSetChanged()
            }
        })



        button.setOnClickListener{
            println(viewModel.user.value)
            viewModel.loadRepos()
        }
    }
}