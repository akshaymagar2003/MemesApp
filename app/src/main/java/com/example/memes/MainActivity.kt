package com.example.memes

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.memes.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
   lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        getData()
//        https://meme-api.com/gimme
        binding.button.setOnClickListener{
            getData()
        }
    }

    private fun getData() {

        val progressDialog=ProgressDialog(this)
        progressDialog.setMessage("Please wait while data is fetch")
        progressDialog.show()


       RetrofitInstance.apiInterface.getData().enqueue(object : Callback<responseDataClass?>{
        override fun onResponse(
            call: Call<responseDataClass?>,response: Response<responseDataClass?>
        ){   binding.textview1.text=response.body()?.title
            binding.textView2.text=response.body()?.author
            Glide.with(this@MainActivity).load(response.body()?.url).into(binding.imageView2);
             progressDialog.dismiss()
        }

           override fun onFailure(call: Call<responseDataClass?>, t: Throwable) {
              Toast.makeText(this@MainActivity,"${t.localizedMessage}",Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
           }


       })
    }
}