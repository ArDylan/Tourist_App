package com.example.touristapps

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.touristapps.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var shareData: () -> Unit

    companion object {
        const val EXTRA_DESTINATION = "destination"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destination: Destination? =
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Destination>(EXTRA_DESTINATION)


        if (destination == null) {
            return finish()
        }

        val (
            name,
            image,
            overview,
            description,
            location,
            detailImage,
            rating,
        ) = destination

        Glide.with(this)
            .load(image)
            .into(binding.destinationImage)

        Glide.with(this)
            .load(detailImage)
            .into(binding.imageDescription)

        binding.destinationDescription.text = description
        binding.destinationName.text = name
        binding.rating.text = rating
        binding.location.text = location

        shareData = {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "destinasi : $name\n" +
                        "location : $location\n" +
                        "Detail : $description\n" +
                        "rating : $rating\n"
            )

            startActivity(Intent.createChooser(shareIntent, "Share using"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        menuInflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_back -> {
                finish()
            }
            R.id.action_share -> {
                shareData()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}