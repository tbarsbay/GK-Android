package org.givingkitchen.android.ui.forms.questionviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_question_textarea.view.*
import org.givingkitchen.android.R
import org.givingkitchen.android.util.setTextIfItExists

class TextareaQuestion(title: String?, answer: String? = null, context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): LinearLayout(context, attrs, defStyle), QuestionView {
    // todo: use merge tags in views
    init {
        LayoutInflater.from(context).inflate(R.layout.view_question_textarea, this, true)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this.orientation = VERTICAL
        title_textareaQuestion.setTextIfItExists(title)
        if (!answer.isNullOrBlank()) {
            text_textareaQuestion.setText(answer)
        }
    }

    override fun isAnswered(): Boolean {
        return text_textareaQuestion.text.isNotBlank()
    }

    override fun placeUnansweredWarning() {
        warning_textareaQuestion.visibility = View.VISIBLE
    }

    override fun getAnswer(): String? {
        val answer = text_textareaQuestion.text.toString()

        return if (answer.isBlank()) {
            null
        } else {
            answer
        }
    }
}