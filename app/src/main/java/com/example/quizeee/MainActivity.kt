package com.example.quizeee

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            if (FirebaseAuth.getInstance().currentUser != null) {
                val intent = Intent(this@MainActivity, QuizActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.putExtra("MODE", "signUp")
                startActivity(intent)
            }
        }, 3000)
    }
}