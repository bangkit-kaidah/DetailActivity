package com.example.capstoneproject.datalistregulationJdhin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.databinding.ActivityDetailJdhinBinding
import com.example.capstoneproject.dataregulation.AdapterRetrofit2
import com.example.capstoneproject.dataregulation.DataSerialized
import com.example.capstoneproject.dataregulation.SpecialSerialized
import com.example.capstoneproject.retrofit.DataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailJdhinActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_JDHIN = "extra_JDHIN"
    }

    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<DataSerialized> = ArrayList()
    private lateinit var adapterp : AdapterRetrofit2
    private lateinit var layoutmanagerrv: LinearLayoutManager

    //projectnext page
    private var page = 1
    private var totalPage = 50
    private var isLoading = false

    private lateinit var binding: ActivityDetailJdhinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailJdhinBinding.inflate(layoutInflater)
        layoutmanagerrv = LinearLayoutManager(this)
        adapterp = AdapterRetrofit2(list)
        recyclerView = binding.rvJdihn

        val gitId = intent.getIntExtra(EXTRA_JDHIN, 0)
        getDataFromAPI(gitId)

        setContentView(binding.root)
    }

    private fun getDataFromAPI(id: Int){
        DataClient.InstanceApi.getRegulationJdhin(id).enqueue(object : Callback<SpecialSerialized>{
            override fun onResponse(
                call: Call<SpecialSerialized>,
                response: Response<SpecialSerialized>
            ) {
                recyclerView.layoutManager = layoutmanagerrv
                recyclerView.adapter = adapterp
                response.body()?.data?.let { adapterp.setterList(it) }
                b()
                adapterp.setOnItemClickCallback(object : AdapterRetrofit2.OnItemClickCallback {
                    override fun onItemClicked(data: DataSerialized) {
                    }
                })
            }

            override fun onFailure(call: Call<SpecialSerialized>, t: Throwable) {
            }

        })
    }

    private fun getNextSearchJdhin(isOnRefreshSearch: Boolean) {
        isLoading = true
        if (isOnRefreshSearch) binding.progressBar.visibility = View.VISIBLE
        val gitId = intent.getIntExtra(EXTRA_JDHIN, 0)
        DataClient.InstanceApi.getDataJDHINSecondary(gitId,page)
            .enqueue(object : Callback<SpecialSerialized>{
                override fun onResponse(
                    call: Call<SpecialSerialized>,
                    response: Response<SpecialSerialized>
                ) {
                    val listrest = response.body()?.data
                    listrest?.let { adapterp.setterList(it) }
                    b()
                    binding.progressBar.visibility = View.GONE
                    isLoading = false
                }

                override fun onFailure(call: Call<SpecialSerialized>, t: Throwable) {
                }
            })
    }

    private fun b(){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleCount = layoutmanagerrv.childCount
                val pastVisibleItem = layoutmanagerrv.findFirstCompletelyVisibleItemPosition()
                val total = adapterp.itemCount

                if (!isLoading && page < totalPage) {
                    if (visibleCount + pastVisibleItem <= total) {
                        page++
                        getNextSearchJdhin(false)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }


}