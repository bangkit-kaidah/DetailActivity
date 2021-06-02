package com.example.capstoneproject.datadetailjdhin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.databinding.ActivityDetailJdhinBinding
import com.example.capstoneproject.datadetail.DetailSerialized
import com.example.capstoneproject.dataregulation.AdapterRetrofit2
import com.example.capstoneproject.dataregulation.DataSerialized
import com.example.capstoneproject.dataregulation.PageRegulationActivity
import com.example.capstoneproject.dataregulation.SpecialSerialized
import com.example.capstoneproject.retrofit.DataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailJdhinActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_JDHIN = "extra_JDHIN"
    }
//adapter harus beda ingat ed !!
    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<DataSerialized> = ArrayList()
    private lateinit var adapterp : AdapterRetrofit2

    private lateinit var binding: ActivityDetailJdhinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailJdhinBinding.inflate(layoutInflater)
        adapterp = AdapterRetrofit2(list)
        recyclerView = binding.rvJdhin
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
                recyclerView.layoutManager = LinearLayoutManager(this@DetailJdhinActivity)
                recyclerView.adapter = adapterp
                response.body()?.data?.let { adapterp.setterList(it) }
                adapterp.setOnItemClickCallback(object : AdapterRetrofit2.OnItemClickCallback {
                    override fun onItemClicked(data: DataSerialized) {
                    }
                })
            }

            override fun onFailure(call: Call<SpecialSerialized>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}