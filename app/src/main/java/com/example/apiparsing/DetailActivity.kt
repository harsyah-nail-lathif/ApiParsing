package com.example.apiparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import coil.api.load
import coil.size.Scale
import com.example.apiparsing.databinding.ActivityDetailBinding
import com.example.apiparsing.databinding.CdvNewsHeadlineBinding
import com.example.apiparsing.model.ArticlesItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cdv_news_headline.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        //untuk membuat layout
        binding.run {
            setContentView(root)

            //untuk membuat actionbar
            setSupportActionBar(toolBar)

            //untuk menambpilkan tombol back
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = data.title
            imgToolbar.apply {
                load(data.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = data.description
            }
            txtContent.text = data.content

            txtDate.text = data.publishedAt
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}