package com.icdominguez.scribbledash.domain

import android.content.Context
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.model.Feedback

class GetRandomFeedbackUseCase(private val context: Context) {
    operator fun invoke(feedback: Feedback): String {
        return when(feedback) {
            Feedback.MEH -> context.resources.getStringArray(R.array.feedback_ops_meh).random()
            Feedback.GREAT -> context.resources.getStringArray(R.array.feedback_great).random()
            Feedback.WOOHOO -> context.resources.getStringArray(R.array.feedback_woohoo).random()
        }
    }
}