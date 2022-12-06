package com.bartukaraca.cointakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cryptomain.*
import kotlinx.android.synthetic.main.activity_cryptomain.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoMain : AppCompatActivity(), RecyclerViewAdapter.Listener {

    //--url 'https://api.coinpaprika.com/v1/tickers'
    private val BASE_URL = "https://api.coinpaprika.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private lateinit var auth: FirebaseAuth


    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cryptomain)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@CryptoMain)
        recyclerView.layoutManager = layoutManager
        auth = Firebase.auth

        loadData()

        bottom_navigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.profile -> startActivity(Intent(this, Profile::class.java))
                R.id.home -> startActivity(Intent(this, CryptoMain::class.java))
            }
        }
    }
    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            recyclerViewAdapter =
                                RecyclerViewAdapter(cryptoModels!!, this@CryptoMain)
                            recyclerView.adapter = recyclerViewAdapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this, "Clicked:${cryptoModel.name}", Toast.LENGTH_LONG).show()
    }

}