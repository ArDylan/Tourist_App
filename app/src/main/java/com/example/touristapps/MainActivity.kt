package com.example.touristapps

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touristapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Destination>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDestination.setHasFixedSize(true)

        list.addAll(getDestinationList())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this@MainActivity, AboutActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList() {
        if(applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDestination.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvDestination.layoutManager = LinearLayoutManager(this)
        }

        val listDestinationAdapter = ListDestinationAdapter(list)
        binding.rvDestination.adapter = listDestinationAdapter
    }

    private fun getDestinationList(): Collection<Destination> {
        val name = resources.getStringArray(R.array.data_name)
        val overview = resources.getStringArray(R.array.data_overview)
        val description = resources.getStringArray(R.array.data_description)
        val image = resources.getStringArray(R.array.data_image)
        val detailImage = resources.getStringArray(R.array.data_detail_image)
        val location = resources.getStringArray(R.array.data_location)
        val rating = resources.getStringArray(R.array.data_rating)

        val listDestination = ArrayList<Destination>()
        for (i in name.indices) {
            listDestination.add(Destination(
                name = name[i],
                overview = overview[i],
                description = description[i],
                image = image[i],
                detailImage = detailImage[i],
                location = location[i],
                rating = rating[i]
            ))
        }

        return listDestination
    }
}