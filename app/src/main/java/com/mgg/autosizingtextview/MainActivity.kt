package com.mgg.autosizingtextview

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.TextViewCompat
import dev.drewhamilton.inlinedimens.dp
import dev.drewhamilton.inlinedimens.widget.setAutoSizeTextTypeUniformWithConfiguration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAutoSizeTextTypeUniformWithConfiguration()
    }


    @SuppressLint("SetTextI18n")
    private fun setAutoSizeTextTypeUniformWithConfiguration() {
        val dpMin = 2.dp
        val dpMax = 10.dp
        val dpGranularity = 2.dp
        val mockTextView = findViewById<TextView>(R.id.textView)
        mockTextView.disableAutoSize()
        val layoutParams = mockTextView.layoutParams
        layoutParams.width = ConstraintLayout.LayoutParams.WRAP_CONTENT
        mockTextView.text = "参会人员(99+)"
        mockTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dp2px(10F).toFloat())
        mockTextView.post {
            val strLength = measureTextSize(mockTextView.text.toString(), mockTextView)
            val width: Int = mockTextView.width
            // 获取省略的字符数，0表示没和省略
            val ellipsisCount: Int? = mockTextView.layout?.getEllipsisCount(mockTextView.lineCount - 1)
            Log.e("mgg", "strLength:$strLength  ellipsisCount:$ellipsisCount")
            if (strLength > width || (ellipsisCount != null && ellipsisCount > 0)) {
                mockTextView.setAutoSizeTextTypeUniformWithConfiguration(dpMin, dpMax, dpGranularity)
            }
        }
    }

    private fun dp2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun measureTextSize(content: String, tv: TextView): Float {
        val oldString = tv.text
        val tp = tv.paint
        tv.text = content
        val length = Layout.getDesiredWidth(tv.text.toString(), 0, tv.text.length, tp)
        tv.text = oldString
        return length
    }

    private fun TextView.disableAutoSize() {
        TextViewCompat.setAutoSizeTextTypeWithDefaults(
            this,
            TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE
        )
    }
}