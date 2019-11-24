package com.example.submissionandroidpemula

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class DataAdapter(private val context: Context, private val data: List<DataModel>?) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_item_main_content, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        data?.get(p1)?.let { p0.bindItem(it) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cvContainer: CardView = itemView.find(R.id.cv_item_container)
        private val ivContent: ImageView = itemView.find(R.id.iv_content)
        private val tvContentName: TextView = itemView.find(R.id.tv_content_name)
        private val tvContentSubtitle: TextView = itemView.find(R.id.tv_content_subtitle)
        fun bindItem(data: DataModel) {
            tvContentName.text = data.dataName
            tvContentSubtitle.text = data.dataPlace
            itemView.context.let {
                Picasso.get()
                    .load(data.dataImage)
                    .into(ivContent)
            }
            cvContainer.setOnClickListener {
                itemView.context.startActivity<DetailActivity>(
                    "data_image" to data.dataImage,
                    "data_name" to data.dataName,
                    "data_location" to data.dataPlace,
                    "data_description" to data.dataDescription
                )
            }
        }
    }

}