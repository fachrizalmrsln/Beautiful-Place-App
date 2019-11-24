package com.example.submissionandroidpemula

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about.*
import org.jetbrains.anko.find

class AboutActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvToolbar: TextView
    private lateinit var dialog: AlertDialog
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        initializeToolbar()
        initDialog()
        Picasso.get()
            .load(R.drawable.profile_picture)
            .into(iv_profile_picture)
    }

    private fun initializeToolbar() {
        toolbar = find(R.id.toolbar_main)
        tvToolbar = find(R.id.tv_title_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvToolbar.text = resources.getString(R.string.about)
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
        }, 1000)
    }
}
