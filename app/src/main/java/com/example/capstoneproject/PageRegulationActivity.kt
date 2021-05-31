package com.example.capstoneproject

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.adapter.AdapterRetrofit2
import com.example.capstoneproject.databinding.ActivityPageRegulationBinding
import com.example.capstoneproject.item.adapter.AdapterRetrofit
import com.example.capstoneproject.item.retrofit.DataClient
import com.example.capstoneproject.item.serialized.DataSerialized
import com.example.capstoneproject.item.serialized.SpecialSerialized
import com.example.capstoneproject.item.serialized.SubjectSerialized
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageRegulationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<DataSerialized> = ArrayList()
    private lateinit var adapterp : AdapterRetrofit2

    private lateinit var binding: ActivityPageRegulationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageRegulationBinding.inflate(layoutInflater)
        setSupportActionBar(binding.tolbar3)
        recyclerView = binding.rvPageRegulation1
        adapterp = AdapterRetrofit2(list)
        addData()
        setContentView(binding.root)
    }



    private fun showSelectedData(data: SubjectSerialized) {
        val githubIntent = Intent(this, DetailActivity::class.java)
        githubIntent.putExtra(DetailActivity.EXTRA_DATA, data)
        startActivity(githubIntent)
    }

    private fun addData(){
        DataClient.InstanceApi.getDataSpecial().enqueue(object : Callback<SpecialSerialized>{
            override fun onResponse(
                call: Call<SpecialSerialized>,
                response: Response<SpecialSerialized>
            ) {
                if (response.isSuccessful) {
                    val date = response.body()?.data
                    recyclerView.layoutManager = LinearLayoutManager(this@PageRegulationActivity)
                    recyclerView.adapter = adapterp
                    date?.let { adapterp.setterList(it) }
                }
            }

            override fun onFailure(call: Call<SpecialSerialized>, t: Throwable) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search = menu.findItem(R.id.search)
        val searchV = search?.actionView as SearchView

        searchV.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchV.queryHint = "Masukkan Data"
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