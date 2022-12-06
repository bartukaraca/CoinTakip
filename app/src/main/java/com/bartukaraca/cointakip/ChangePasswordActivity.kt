package com.bartukaraca.cointakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bartukaraca.cointakip.databinding.ActivityChangePasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

    }

    fun backProfile(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }

    fun changePassword(view: View) {
        val user = Firebase.auth.currentUser
        val newPassword = binding.editTextTextPassword.text.toString()

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Your password has been changed", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }
}