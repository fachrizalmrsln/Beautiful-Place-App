package com.example.submissionandroidpemula

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.find

class DetailActivity : AppCompatActivity() {

    private lateinit var dialog: AlertDialog
    private lateinit var builder: AlertDialog.Builder
    private var dataImage = 0
    private var dataName = "Default"
    private var dataLocation = "Default"
    private var dataDescription = "Default"

    private lateinit var ivDetail: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getData()
        initializeViews()
        bindData()
        initDialog()
    }

    private fun initializeViews() {
        ivDetail = find(R.id.iv_detail)
        tvName = find(R.id.tv_name_detail)
        tvLocation = find(R.id.tv_location_detail)
        tvDescription = find(R.id.tv_description_detail)
        iv_back_detail.setOnClickListener {
            finish()
        }
    }

    private fun getData() {
        dataImage = intent.getIntExtra("data_image", 0)
        dataName = intent.getStringExtra("data_name")!!
        dataLocation = intent.getStringExtra("data_location")!!
        dataDescription = intent.getStringExtra("data_description")!!
    }

    @SuppressLint("InflateParams")
    private fun initDialog() {
        val v: View? = LayoutInflater.from(this)
            .inflate(R.layout.progress_loading, null)
        builder = AlertDialog.Builder(this)
        builder.setView(v)
            .setCancelable(false)
        dialog = builder.create()
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()
        }, 1500)
    }

    private fun bindData() {
        Picasso.get()
            .load(dataImage)
            .into(ivDetail)

        tvName.text = dataName
        tvLocation.text = dataLocation
        tvDescription.text = dataDescription
    }
}
