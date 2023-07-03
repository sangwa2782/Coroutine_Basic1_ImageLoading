package com.ajayam.coroutine_basic1_imageloading

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLoadImage = findViewById<Button>(R.id.btn_load_image)
        val imageView = findViewById<ImageView>(R.id.im_image)

        btnLoadImage.setOnClickListener{
           CoroutineScope(Dispatchers.IO).launch {
               Log.d("MyTag", "onCreate: ThreadName: ${Thread.currentThread().name}")

               val url = URL("https://img.freepik.com/free-photo/fruit-salad-spilling-floor-was-mess-vibrant-colors-textures-generative-ai_8829-2895.jpg?w=740&t=st=1688360662~exp=1688361262~hmac=9196c023d4b2a2afe6e4f97ff85ff0a3b7c70f69eef867dc5d26a15033b1b391")
               val bitmap = BitmapFactory.decodeStream(url.openStream())

               withContext(Dispatchers.Main){
                   Log.d("MyTag", "onCreate withContext: ThreadName: ${Thread.currentThread().name}")
                   imageView.setImageBitmap(bitmap)
               }

           }
        }
    }
}