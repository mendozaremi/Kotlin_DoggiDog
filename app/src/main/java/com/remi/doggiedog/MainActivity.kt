package com.remi.doggiedog

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    // ColorWheel color Class
    private val colorWheel = ColorWheel()

    // Declare View Variable
    private var constraintLayout: ConstraintLayout? = null

    // creates main layout with button and img resource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val changeDogButton: Button = findViewById(R.id.button)
        // assign view from layout files to the corresponding variables
        constraintLayout = findViewById(R.id.constraintLayout)

        viewModel.currentlyDisplayedImage.observe( this,
            {
                val mainImage: ImageView = findViewById(R.id.DogImageHolder)
                //  convert uri load image source url
                Picasso.with(this).load(it.imgSrcUrl).into(mainImage)
            })


        changeDogButton.setOnClickListener {
            val color = colorWheel.getColor()
            val color2 = colorWheel.getColor()
            // call getNewDog Function
            viewModel.getNewDog()

            // change button color
            changeDogButton.setBackgroundColor(color)

            // change background color
            constraintLayout!!.setBackgroundColor(color2)
        }
    }
}