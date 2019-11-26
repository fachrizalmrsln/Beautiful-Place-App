package com.example.submissionandroidpemula

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvToolbar: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var dialog: AlertDialog
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDialog()
        initializeToolbar()
        initializeViews()
        addData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                startActivity<AboutActivity>()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeToolbar() {
        toolbar = find(R.id.toolbar_main)
        tvToolbar = find(R.id.tv_title_toolbar)
        setSupportActionBar(toolbar)
        tvToolbar.text = resources.getString(R.string.home)
    }

    private fun initializeViews() {
        recyclerView = find(R.id.rv_main_content)
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
        }, 4000)
    }

    fun addData() {
        val data: ArrayList<DataModel> = ArrayList()
        GlobalScope.launch {
            data.add(
                0, DataModel(
                    dataImage = R.drawable.maldives,
                    dataName = "Maldives",
                    dataPlace = "Arabian Sea",
                    dataDescription = resources.getString(R.string.maldives_description)
                )
            )
            data.add(
                1, DataModel(
                    dataImage = R.drawable.bora_bora,
                    dataName = "Bora Bora",
                    dataPlace = "Polynesia, France",
                    dataDescription = resources.getString(R.string.bora_bora_description)
                )
            )
            data.add(
                2, DataModel(
                    dataImage = R.drawable.bromo,
                    dataName = "Mount Bromo",
                    dataPlace = "East Java",
                    dataDescription = resources.getString(R.string.mount_bromo_description)
                )
            )
            data.add(
                3, DataModel(
                    dataImage = R.drawable.colosseum,
                    dataName = "Colosseum",
                    dataPlace = "Rome, Italy",
                    dataDescription = resources.getString(R.string.colosseum_description)
                )
            )
            data.add(
                4, DataModel(
                    dataImage = R.drawable.eiffel,
                    dataName = "Eiffel Tower",
                    dataPlace = "Paris, France",
                    dataDescription = resources.getString(R.string.eiffel_tower_description)
                )
            )
            data.add(
                5, DataModel(
                    dataImage = R.drawable.labuan_bajo,
                    dataName = "Labuan Bajo",
                    dataPlace = "Nusa Tenggara",
                    dataDescription = resources.getString(R.string.labuan_bajo_description)
                )
            )
            data.add(
                6, DataModel(
                    dataImage = R.drawable.mount_fuji,
                    dataName = "Mount Fuji",
                    dataPlace = "Japan",
                    dataDescription = resources.getString(R.string.mount_fuji_description)
                )
            )
            data.add(
                7, DataModel(
                    dataImage = R.drawable.santorini,
                    dataName = "Santorini",
                    dataPlace = "Greece",
                    dataDescription = resources.getString(R.string.santorini_description)
                )
            )
            data.add(
                8, DataModel(
                    dataImage = R.drawable.jeju_island,
                    dataName = "Jeju Island",
                    dataPlace = "South Korea",
                    dataDescription = resources.getString(R.string.jeju_island)
                )
            )
            data.add(
                9, DataModel(
                    dataImage = R.drawable.greatest_wall,
                    dataName = "Great Wall",
                    dataPlace = "China",
                    dataDescription = resources.getString(R.string.great_wall_description)
                )
            )
        }
        setRecyclerView(data)
    }

    private fun setRecyclerView(data: List<DataModel>) {
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = DataAdapter(this, data)
    }

}
