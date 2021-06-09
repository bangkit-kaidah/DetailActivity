package com.example.capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.capstoneproject.datadetail.DetailActivity
import com.example.capstoneproject.datajdhin.JdhinActivity
import com.example.capstoneproject.datasubject.SubjectSerializedActivity

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.Toolbar1))
        val text = findViewById<TextView>(R.id.text_main)
        text.text = "Main Activity"

        button = findViewById(R.id.button)
        button.setOnClickListener{
            val result = Intent(this, SubjectSerializedActivity::class.java)
            startActivity(result)
        }

        val button1:Button = findViewById(R.id.button2)
        button1.setOnClickListener{
            val res = Intent(this,  JdhinActivity::class.java)
            startActivity(res)
        }

        val button2:Button = findViewById(R.id.button3)
        button2.setOnClickListener{
            val res = Intent(this,  DetailActivity::class.java)
            startActivity(res)
        }
    }
}