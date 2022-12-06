package com.bartukaraca.cointakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bartukaraca.cointakip.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cryptomain.*
import kotlinx.android.synthetic.main.activity_cryptomain.bottom_navigation
import kotlinx.android.synthetic.main.activity_profile.*

@Suppress("DEPRECATION")
class Profile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        val user=Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                val name = profile.displayName
                binding.name.text = name.toString()
                val email = profile.email
                binding.emailtext4.text = email.toString()

            }
        }
        bottom_navigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.profile -> startActivity(Intent(this, Profile::class.java))
                R.id.home -> startActivity(Intent(this, CryptoMain::class.java))

            }

        }
    }

    fun cik(view: View) {
        auth.signOut()
        val intent = Intent(this@Profile, MainActivity::class.java)
        startActivity(intent)
        finish()
        finishAffinity()

    }

    fun deleteAccount(view: View) {
        auth.currentUser?.delete()?.addOnCompleteListener {
            val intent = Intent(this@Profile, MainActivity::class.java)
            startActivity(intent)
            finish()
            finishAffinity()

        }
    }
    fun changeName(view: View){
        val intent = Intent(this@Profile,ChangeNameActivity::class.java)
        startActivity(intent)
        finish()
   }
    fun changeEmail(view: View) {
            val intent = Intent(this@Profile, EmailActivity::class.java)
            startActivity(intent)
            finish()
        }
    fun changePassword(view: View){
        val intent=Intent(this@Profile,ChangePasswordActivity::class.java)
        startActivity(intent)
        finish()
    }


}
