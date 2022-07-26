package com.example.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(int: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult.countOfRightAnswers, gameResult.countOfQuestions)
    )
}

private fun getPercentOfRightAnswers(countOfRightAnswers: Int, countOfQuestions: Int): Int {
    return if(countOfQuestions == 0){
        0
    }else{
        ((countOfRightAnswers / countOfQuestions.toDouble())*100).toInt()
    }
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, isWon: Boolean){
    imageView.setImageResource(getSmileResId(isWon))
}

private fun getSmileResId(isWon: Boolean) : Int {
    return if(isWon){
        R.drawable.ic_smile
    }
    else {
        R.drawable.ic_sad
    }
}

@BindingAdapter("progressBar")
fun bindProgressBar(progressBar: ProgressBar, percentOfRightAnswers: Int){
    progressBar.setProgress(percentOfRightAnswers, true)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int{
    val colorResId = if(goodState) {
        android.R.color.holo_green_light
    }
    else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}