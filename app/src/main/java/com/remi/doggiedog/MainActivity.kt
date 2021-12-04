package com.remi.doggiedog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.remi.doggiedog.database.DogEntity
import com.remi.doggiedog.viewmodels.MainViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((application as DogApplication).database.dogDao())
    }

    // ColorWheel color Class
    private val colorWheel = ColorWheel()

    // Declare View Variable
    private var constraintLayout: ConstraintLayout? = null

    // creates main layout with button and img resource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // grab references to both buttons
        val changeDogButton: Button = findViewById(R.id.button)
        val previousDogButton: Button = findViewById(R.id.previousBtn)

        // assign view from layout files to the corresponding variables
        constraintLayout = findViewById(R.id.constraintLayout)

        viewModel.currentlyDisplayedImage.observe( this,
            {
                val mainImage: ImageView = findViewById(R.id.DogImageHolder)

                //  convert uri load image source url using image caching library Picasso
                Picasso.with(this).load(it.imgSrcUrl).into(mainImage)
            })


        changeDogButton.setOnClickListener {
            val color = colorWheel.getColor()
            val color2 = colorWheel.getColor()

            val currentDogUrl = viewModel.currentlyDisplayedImage.value?.imgSrcUrl
            val newDogImage = currentDogUrl?.let { it1 -> DogEntity(url = it1) }

            // call getNewDog Function
            viewModel.getNewDog()

            // if new dogimage does not equal null call newDogImage function
            if(newDogImage != null) {
                viewModel.addDog(newDogImage)
            }
            // delete reference to newest added dog in database
            viewModel.deleteMostRecentDog()

            // change button color
            changeDogButton.setBackgroundColor(color)

            // null assertion operator
            // change background color
            constraintLayout!!.setBackgroundColor(color2)
        }

        // onsetclick listener for previous button pass intent mainactivity 2
        previousDogButton.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
    }
}