package com.example.memesapp

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMeme()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{ getMeme() }
    }

    private fun getMeme() {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val memeImageView  = findViewById<ImageView>(R.id.imageView)
        val url = "https://meme-api.com/gimme"
        val queue = Volley.newRequestQueue(this)

        progressBar.visibility = View.VISIBLE
        memeImageView.visibility = View.GONE
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val imgUrl = response.getString("url")
                Glide.with(this).load(imgUrl).into(memeImageView)
                progressBar.visibility = View.GONE
                memeImageView.visibility = View.VISIBLE
            }, { error ->
                Toast.makeText(applicationContext, "Error with uploading a meme", LENGTH_SHORT).show()
        })
        queue.add(jsonObjectRequest)
    }
}