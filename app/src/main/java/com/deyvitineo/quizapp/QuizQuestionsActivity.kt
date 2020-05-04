package com.deyvitineo.quizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<QuestionModel>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mNextQuestion: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions()
        setQuestion()

        tv_answer_one.setOnClickListener(this)
        tv_answer_two.setOnClickListener(this)
        tv_answer_three.setOnClickListener(this)
        tv_answer_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    private fun setQuestion() {
        defaultOptionsView()
        mNextQuestion = false
        val question = mQuestionsList!![mCurrentPosition - 1]

        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }

        progress_bar.progress = mCurrentPosition
        val progressText = "$mCurrentPosition/ ${progress_bar.max}"
        tv_progress.text = progressText

        tv_question.text = question.question
        iv_flag.setImageResource(question.image)
        tv_answer_one.text = question.optionOne
        tv_answer_two.text = question.optionTwo
        tv_answer_three.text = question.optionThree
        tv_answer_four.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_answer_one)
        options.add(1, tv_answer_two)
        options.add(2, tv_answer_three)
        options.add(3, tv_answer_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_answer_one -> selectedOptionView(tv_answer_one, 1)
            R.id.tv_answer_two -> selectedOptionView(tv_answer_two, 2)
            R.id.tv_answer_three -> selectedOptionView(tv_answer_three, 3)
            R.id.tv_answer_four -> selectedOptionView(tv_answer_four, 4)
            R.id.btn_submit -> {
                //Ensures the user does not submit without an answer
                if (mSelectedOptionPosition <= 0 && !mNextQuestion) {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                    return
                }

                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(
                                this,
                                "You have successfully completed the quiz",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "Next Question"
                        mNextQuestion = true
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> tv_answer_one.background = ContextCompat.getDrawable(this, drawableView)
            2 -> tv_answer_two.background = ContextCompat.getDrawable(this, drawableView)
            3 -> tv_answer_three.background = ContextCompat.getDrawable(this, drawableView)
            4 -> tv_answer_four.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

}
