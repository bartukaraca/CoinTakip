package com.bartukaraca.cointakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bartukaraca.cointakip.databinding.ActivityChangeNameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class ChangeNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangeNameBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
    }

    fun backProfile(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }

    fun changeName(view: View) {
        val user = Firebase.auth.currentUser
        val name = binding.editTextTextPersonName.text.toString()
        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }
}


