package com.example.quizeee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizeee.databinding.ActivityQuizBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var list: ArrayList<QuestionModel>
    private var count: Int = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()

        // Fetch quiz questions from Firebase Firestore
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("quiz")
            .get()
            .addOnSuccessListener { querySnapshot ->
                list.clear()
                for (document: DocumentSnapshot in querySnapshot) {
                    val questionModel = document.toObject(QuestionModel::class.java)
                    if (questionModel != null) {
                        list.add(questionModel)
                    }
                }

                // Once the questions are fetched, show the first question
                showQuestion()
            }
            .addOnFailureListener { exception ->
                // Handle any error that occurred during fetching questions
                // For example, show an error message or retry fetching
            }

        binding.option1.setOnClickListener {
            nextData(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener {
            nextData(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener {
            nextData(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener {
            nextData(binding.option4.text.toString())
        }
    }

    private fun showQuestion() {
        if (count < list.size) {
            val question = list[count]
            binding.question.text = question.question
            binding.option1.text = question.option1
            binding.option2.text = question.option2
            binding.option3.text = question.option3
            binding.option4.text = question.option4
        }
    }

    private fun nextData(selectedAnswer: String) {
        if (count < list.size) {
            val currentQuestion = list[count]
            if (currentQuestion.ans!!.equals(selectedAnswer, ignoreCase = true)) {
                score++
            }
        }
        count++
        if (count >= list.size) {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        } else {
            showQuestion()
        }
    }
}
