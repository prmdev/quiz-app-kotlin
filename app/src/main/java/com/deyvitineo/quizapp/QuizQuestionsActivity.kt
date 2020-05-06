package com.deyvitineo.quizapp

import android.content.Intent
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
    private var mUsername: String? = null
    private var mCorrectAnswers: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUsername = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestions()
        setQuestion()

        btn_submit.setOnClickListener(this)
        btn_next.setOnClickListener(this)
        btn_finish.setOnClickListener(this)
    }

    // Sets questions and updates progress bar
    private fun setQuestion() {
        defaultOptionsView()

        addAnswersListeners()
        val question = mQuestionsList!![mCurrentPosition - 1]

        //update progress bar
        progress_bar.progress = mCurrentPosition
        val progressText = "$mCurrentPosition/ ${progress_bar.max}"
        tv_progress.text = progressText

        //set values for question, image and answers
        tv_question.text = question.question
        iv_flag.setImageResource(question.image)
        tv_answer_one.text = question.optionOne
        tv_answer_two.text = question.optionTwo
        tv_answer_three.text = question.optionThree
        tv_answer_four.text = question.optionFour
    }

    //Set default view for answers
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
                if (mSelectedOptionPosition <= 0) {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                    return
                }

                btn_submit.visibility = View.GONE
                removeAnswersListeners() //removes the ability to change answers after submitting

                if (mCurrentPosition == mQuestionsList!!.size) { //finish the quiz
                    btn_finish.visibility = View.VISIBLE
                } else {
                    btn_next.visibility = View.VISIBLE //go to next question
                }

                val question = mQuestionsList?.get(mCurrentPosition - 1)
                if (question!!.correctAnswer != mSelectedOptionPosition) {
                    answerViewUpdate(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                } else {
                    mCorrectAnswers++
                }
                answerViewUpdate(question.correctAnswer, R.drawable.correct_option_border_bg)
            }
            R.id.btn_next -> {
                btn_next.visibility = View.GONE
                btn_submit.visibility = View.VISIBLE
                mCurrentPosition++
                mSelectedOptionPosition = 0
                sv_scroll_view_questions.requestFocus(View.FOCUS_UP)
                sv_scroll_view_questions.scrollTo(0,0)
                setQuestion()
            }
            R.id.btn_finish -> {
                btn_next.visibility = View.GONE
                btn_submit.visibility = View.GONE

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(Constants.USER_NAME, mUsername)
                intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                startActivity(intent)
                finish()
            }
        }
    }

    //Changes background of a selected answer to the proper style
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }


    /**
     * Updates the background and style of the textview based on the information passed
     * @param answer the answer selected by the user: used to determine which textview to update
     * @param drawableView the drawable file to be used for the view
     */
    private fun answerViewUpdate(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> tv_answer_one.background = ContextCompat.getDrawable(this, drawableView)
            2 -> tv_answer_two.background = ContextCompat.getDrawable(this, drawableView)
            3 -> tv_answer_three.background = ContextCompat.getDrawable(this, drawableView)
            4 -> tv_answer_four.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    //Removes listeners from textviews to avoid changing answers after submitting
    private fun removeAnswersListeners(){
        tv_answer_one.setOnClickListener(null)
        tv_answer_two.setOnClickListener(null)
        tv_answer_three.setOnClickListener(null)
        tv_answer_four.setOnClickListener(null)
    }

    //adds listeners to textviews
    private fun addAnswersListeners(){
        tv_answer_one.setOnClickListener(this)
        tv_answer_two.setOnClickListener(this)
        tv_answer_three.setOnClickListener(this)
        tv_answer_four.setOnClickListener(this)
    }
}
