package com.example.memes

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
//        https://meme-api.com/gimme
    }

    private fun getData() {

        val progressDialog=ProgressDialog(this)
        progressDialog.setMessage("Please wait while data is fetch")
        progressDialog.show()


       RetrofitInstance.apiInterface.getData().enqueue(object : Callback<responseDataClass?>{
        override fun onResponse(
            call: Call<responseDataClass?>,response: Response<responseDataClass?>
        ){
             progressDialog.dismiss()
        }

           override fun onFailure(call: Call<responseDataClass?>, t: Throwable) {
              Toast.makeText(this@MainActivity,"${t.localizedMessage}",Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
           }


       })
    }
}