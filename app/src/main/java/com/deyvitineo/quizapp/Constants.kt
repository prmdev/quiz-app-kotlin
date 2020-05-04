package com.deyvitineo.quizapp

object Constants {

    fun getQuestions(): ArrayList<QuestionModel> {
        val questionsList = ArrayList<QuestionModel>()

        val questionOne = QuestionModel(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina, "Argentina",
            "Dominican republic", "Peru", "Mexico",
            1
        )
        questionsList.add(questionOne)

        val questionTwo = QuestionModel(
            2, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Angola", "Austria",
            "Australia", "Armenia", 3

        )
        questionsList.add(questionTwo)

        val questionThree = QuestionModel(
            3, "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Belarus", "Belize",
            "Brunei", "Brazil", 4
        )
        questionsList.add(questionThree)

        val questionFour = QuestionModel(
            4, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Bahamas", "Belgium",
            "Barbados", "Belize", 2
        )
        questionsList.add(questionFour)

        val questionFive = QuestionModel(
            5, "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Gabon", "France",
            "Fiji", "Finland", 3
        )
        questionsList.add(questionFive)

        val questionSix = QuestionModel(
            6, "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Germany", "Georgia",
            "Greece", "none of these", 1
        )
        questionsList.add(questionSix)

        val questionSeven = QuestionModel(
            7, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Dominica", "Egypt",
            "Denmark", "Ethiopia", 3
        )
        questionsList.add(questionSeven)

        val questionEight = QuestionModel(
            8, "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Ireland", "Iran",
            "Hungary", "India", 4

        )
        questionsList.add(questionEight)

        val questionNine = QuestionModel(
            9, "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Australia", "New Zealand",
            "Tuvalu", "United States of America", 2
        )
        questionsList.add(questionNine)

        val questionTen = QuestionModel(
            10, "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Kuwait", "Jordan",
            "Sudan", "Palestine", 1
        )
        questionsList.add(questionTen)

        return questionsList
    }
}