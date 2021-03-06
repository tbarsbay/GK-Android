package org.givingkitchen.android.ui.forms.questionviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_question_shortname.view.*
import org.givingkitchen.android.R
import org.givingkitchen.android.util.setTextIfItExists

class ShortnameQuestion(title: String?, answer: String? = null, context: Context, attrs: AttributeSet? = null, defStyle: Int = 0): LinearLayout(context, attrs, defStyle), QuestionView {
    // todo: use merge tags in views
    init {
        LayoutInflater.from(context).inflate(R.layout.view_question_shortname, this, true)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this.orientation = VERTICAL
        title_shortnameQuestion.setTextIfItExists(title)
    }

    override fun isAnswered(): Boolean {
        return (firstName_shortnameQuestion.text.isNotBlank() && lastName_shortnameQuestion.text.isNotBlank())
    }

    override fun placeUnansweredWarning() {
        warning_shortnameQuestion.visibility = View.VISIBLE
    }

    override fun getAnswer(): String? {
        val address = arrayListOf<String>()

        val firstNameFieldValue = getTextFieldValue(firstName_shortnameQuestion.text)
        if (firstNameFieldValue != null) {
            address.add(firstNameFieldValue)
        } else {
            return null
        }

        val lastNameFieldValue = getTextFieldValue(lastName_shortnameQuestion.text)
        if (lastNameFieldValue != null) {
            address.add(lastNameFieldValue)
        } else {
            return null
        }

        val fullName = address.toString()
        return if (fullName.isNotBlank()) {
            fullName
        } else {
            null
        }
    }

    private fun getTextFieldValue(answer: CharSequence): String? {
        return if (answer.isNotBlank()) {
            answer.toString()
        } else {
            null
        }
    }
}