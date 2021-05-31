package com.example.capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import com.example.capstoneproject.databinding.ActivityDetailBinding
import com.example.capstoneproject.item.retrofit.DataClient
import com.example.capstoneproject.item.serialized.DataSerialized
import com.example.capstoneproject.item.serialized.DetailSerialized
import com.example.capstoneproject.item.serialized.SpecialSerialized
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setSupportActionBar(binding.Toolbar)
        binding.textToolbar.text = "Detail Activity"
        addDataFromAPI()
        backButton()
        setContentView(binding.root)
    }

    private fun addDataFromAPI() {
        DataClient.InstanceApi.getDataSpecial().enqueue(object : Callback<SpecialSerialized>{
            override fun onResponse(
                call: Call<SpecialSerialized>,
                response: Response<SpecialSerialized>
            ) {
                val data = response.body()?.data
                data?.let { getDataSpecial(it) }

            }

            override fun onFailure(call: Call<SpecialSerialized>, t: Throwable) {
            }

        })
    }

    private fun getDataSpecial(id: ArrayList<DataSerialized>){
        for (i in id){
            binding.sourceDetail.text = i.title
            binding.dateDetermination.text = i.status.name
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share) {
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, "Share App and Share Love")
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }

        if (item.itemId == R.id.transate) {
            val languageIntent = Intent(this, MainActivity::class.java)
            startActivity(languageIntent)
        }

        else {
            return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun backButton() {
        binding.button3.setOnClickListener {
            onBackPressed()
        }
    }
}