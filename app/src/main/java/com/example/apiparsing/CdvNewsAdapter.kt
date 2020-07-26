package com.example.apiparsing

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.example.apiparsing.databinding.CdvNewsHeadlineBinding
import com.example.apiparsing.model.ArticlesItem

class CdvNewsAdapter : RecyclerView.Adapter<CdvNewsViewHolder>(){

    //untuk mengambil data di dalam Article Item
    private val listdata = ArrayList<ArticlesItem>()

    //menambakn data di dalam recycler view
    fun addData(items: List<ArticlesItem>){
        listdata.clear()
        listdata.addAll(items)
        notifyDataSetChanged()
    }

    //berfungsi untuk menyatukan atau merapih kan data di dalam recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater, parent, false)
        return CdvNewsViewHolder(binding)
    }

    //berfungsi sebagai indikator panjang atau banyaknya suatu data dengan tujuan looping
    override fun getItemCount(): Int {
    return listdata.size
    }

    //berfungsi sebagai peng alokasi tata letak widge
    override fun onBindViewHolder(holder: CdvNewsViewHolder, position: Int) {
        holder.bind(listdata[position])
    }

}
//sebagai adapter RecyclerView
class CdvNewsViewHolder(private val binding: CdvNewsHeadlineBinding):
RecyclerView.ViewHolder(binding.root){
    fun bind(article : ArticlesItem){
     binding.run {
         txtTitle.text = croptText(article.title?: "tidak ada judul")
         txtSubtitle.text = article.publishedAt
         imgHeadline.apply {
             load(article.urlToImage){
                 scale(Scale.FILL)
             }

             contentDescription = article.description

         }
         //untuk aksi klik pada gambar
         root.setOnClickListener {
             val intent = Intent(it.context, DetailActivity::class.java).apply {
                 putExtra(DetailActivity.DETAIL_NEWS, article)
//                 Detail news berfungsi sebagai variable yang akan di panggil dari derail activity
             }
             it.context.startActivity(intent)
         }
     }
    }
    private fun croptText(text: String): String{
        return text.take(50) + if (text.length > 50) "..." else ""
    }
}
