package com.bartukaraca.cointakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bartukaraca.cointakip.R
import com.bartukaraca.cointakip.databinding.ActivityEmailBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmailBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

    }

    fun backProfile(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }

    fun clickedChangeEmail(view: View) {
        val user = Firebase.auth.currentUser
        val email = binding.enterEmail.text.toString()
        user!!.updateEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Your email has been changed", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    finish()
                }
            }

    }
}


