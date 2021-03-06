package com.example.capstoneproject.datadetail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.ActivityDetailBinding
import com.example.capstoneproject.datahistory.AdapterHistory
import com.example.capstoneproject.datahistory.HistoryActivity
import com.example.capstoneproject.dataregulation.DataSerialized
import com.example.capstoneproject.dataregulation.PageRegulationActivity
import com.example.capstoneproject.datasubject.SubjectSerialized
import com.example.capstoneproject.retrofit.DataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<DataHistory> = ArrayList()
    private lateinit var adapterHIstory: AdapterHistory

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setSupportActionBar(binding.Toolbar)

        binding.textToolbar.text = getString(R.string.title_detail)
        recyclerView = binding.rvHistory123
        adapterHIstory = AdapterHistory(list)

        val gitId = intent.getIntExtra(EXTRA_DATA, 0)
        addDetailAPI(gitId)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    //get Detail Data
    private fun addDetailAPI(id: Int) {
        DataClient.InstanceApi.getDetail(id).enqueue(object : Callback<List<DetailSerialized>> {
            override fun onResponse(
                call: Call<List<DetailSerialized>>,
                response: Response<List<DetailSerialized>>
            ) {
                val data = response.body()
                data?.let { getDetailAPI(it) }

            }

            override fun onFailure(call: Call<List<DetailSerialized>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getDetailAPI(id: List<DetailSerialized>) {
        for (i in id) {
            binding.apply {
                title.text = i.titleDocument
                placeDetermination.text = i.placeDetermination
                dateDetermination.text = i.dateDetermination
                number.text = i.numberRegulation
                dateRelease.text = i.dateRelease
                subject.text = i.subject.name
                source.text = i.source.name
                kindRegulation.text = i.link
                type.text = i.type.name
                status.text = i.status.name
                fabutton.setOnClickListener {
                    if (i.sourceDetail.isNotEmpty()){
                        val a = Intent(Intent.ACTION_VIEW, Uri.parse(i.sourceDetail))
                        startActivity(a)
                    } else {
                        Toast.makeText(applicationContext, "tidak ada data", Toast.LENGTH_SHORT).show()
                    }
                }
                shareToolbar.setOnClickListener {
                    val shareIntent = Intent().apply {
                        this.action = Intent.ACTION_SEND
                        this.putExtra(
                            Intent.EXTRA_TEXT,
                            "Data Yang Anda Butuhkan ${i.sourceDetail}"
                        )
                        this.type = "text/plain"
                    }
                    startActivity(shareIntent)
                }
                addHistoryAPI(i.id)
            }
        }
    }


    //untuk option menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.transate) {
            val languageIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(languageIntent)
        } else {
            return super.onOptionsItemSelected(item)
        }
        return true
    }

    //History
    private fun addHistoryAPI(id: Int) {
        DataClient.InstanceApi.getDataHistory(id)
            .enqueue(object : Callback<List<DetailSerialized>> {
                override fun onResponse(
                    call: Call<List<DetailSerialized>>,
                    response: Response<List<DetailSerialized>>
                ) {
                    val data = response.body()
                    recyclerView.layoutManager = LinearLayoutManager(this@DetailActivity)
                    recyclerView.adapter = adapterHIstory
                    if (data != null) {
                        for (i in data) {
                            val a = i.related
                            adapterHIstory.setterList(a)
                            addAdapterDetail()
                        }
                    }
                }

                override fun onFailure(call: Call<List<DetailSerialized>>, t: Throwable) {
                }

            })
    }

    private fun addAdapterDetail() {
        adapterHIstory.setOnItemClickCallback(object : AdapterHistory.OnItemClickCallback {
            override fun onItemClicked(data: DataHistory) {
                showSelectedDate(data)
            }
        })
    }

    private fun showSelectedDate(date: DataHistory) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_DATA, date.id)
        startActivity(intent)
    }
}