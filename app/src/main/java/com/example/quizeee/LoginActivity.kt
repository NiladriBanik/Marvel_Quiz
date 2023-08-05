package com.example.quizeee
// Add this import statement
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizeee.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportActionBar!!.hide()
        binding!!.button.setOnClickListener { view ->
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding!!.Email.text.toString(),
                binding!!.password.text.toString()
            )
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "User Created!", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this@LoginActivity, "User Not Created!", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }
}

