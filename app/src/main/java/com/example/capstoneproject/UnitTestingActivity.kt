package com.example.capstoneproject

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.databinding.ActivityUnitTestingBinding
import com.example.github.AdapterGithub
import com.example.github.ui.resource.Github
import com.example.github.ui.resource.Resources

class UnitTestingActivity : AppCompatActivity() {

    private lateinit var rvGithub: RecyclerView
    private var list: ArrayList<Github> = arrayListOf()
    private lateinit var binding: ActivityUnitTestingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnitTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.tolbar))
        rvGithub = binding.rvPageRegulation
        rvGithub.setHasFixedSize(true)
        showRecyclerList()
        list.addAll(Resources.phone())
    }

    private fun showRecyclerList() {
        rvGithub.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = AdapterGithub(list)
        rvGithub.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : AdapterGithub.OnItemClickCallback {
            override fun onItemClicked(data: Github) {
            }
        })
    }


    //untuk search
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search = menu.findItem(R.id.search)
        val searchV = search?.actionView as SearchView

        searchV.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchV.queryHint = "kkk"
        searchV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchV.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

}