package com.deyvitineo.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, -1)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, -1)
        val username = intent.getStringExtra(Constants.USER_NAME)
        val percentage = Constants.getAverage(correctAnswers.toDouble(), totalQuestions.toDouble())

        val score = "You scored $correctAnswers out of $totalQuestions"

        if (percentage >= 70) {
            iv_trophy.visibility = View.VISIBLE
            iv_sad_face.visibility = View.GONE

            val percentageMessage = "You got $percentage %!!"
            tv_percentage.text = percentageMessage
            val congratsMessage = "Congratulations $username!"
            tv_congratulations.text = congratsMessage
        } else {
            iv_trophy.visibility = View.GONE
            iv_sad_face.visibility = View.VISIBLE

            val percentageMessage = "You got $percentage% :("
            tv_percentage.text = percentageMessage
            val congratsMessage = "Unfortunately $username, you've failed the quiz!"
            tv_congratulations.text = congratsMessage
        }

        tv_score.text = score

        btn_try_again.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
