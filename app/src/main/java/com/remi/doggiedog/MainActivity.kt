package com.remi.doggiedog

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    // creates main layout with button and img resource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val changeDogButton: Button = findViewById(R.id.button)

        viewModel.currentlyDisplayedImage.observe( this,
            {
                val mainImage: ImageView = findViewById(R.id.DogImageHolder)
                //  convert uri load image source url
                Picasso.with(this).load(it.imgSrcUrl).into(mainImage)
            })


        changeDogButton.setOnClickListener {
            viewModel.getNewDog()
        }
    }
}