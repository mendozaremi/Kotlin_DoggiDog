package com.remi.doggiedog


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.remi.doggiedog.viewmodels.MainViewModel
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels{
        MainViewModel.MainViewModelFactory((application as DogApplication).database.dogDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.previous_dog)

        viewModel.getAllDogs().observe(this){
            val imageView = findViewById<ImageView>(R.id.DogImageHolder2)
            Picasso.with(this).load(it.get(0).url).into(imageView)
            val button: Button = findViewById(R.id.previousBtn)
            button.setOnClickListener {
                finish()
            }
        }
    }
}
