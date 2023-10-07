package com.example.pantrypal.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.pantrypal.R

class EditTextCustomView : AppCompatEditText, View.OnTouchListener{
    private lateinit var searchButton: Drawable
    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private fun init() {
        // Menginisialisasi gambar search button
        searchButton = ContextCompat.getDrawable(context, R.drawable.close) as Drawable
        // Menambahkan aksi kepada search button
        setOnTouchListener(this)

        background = ContextCompat.getDrawable(context, R.drawable.edit_text)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

                //button search
                if (p0.toString().isNotEmpty()) showSearchButton() else hideSearchButton()

                val inputValue = p0.toString()

//                when {
//                    inputValue.length <= 4 -> error =
//                        context.getString(R.string.error)
//                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    // Menampilkan search button
    private fun showSearchButton(){
        setButtonDrawables(endOfTheText = searchButton)
    }

    //menghilangkan search button
    private fun hideSearchButton(){
        setButtonDrawables()
    }

    //Mengkonfigurasi button
    private fun setButtonDrawables(startOfTheText: Drawable? = null, topOfTheText:Drawable? = null, endOfTheText:Drawable? = null, bottomOfTheText: Drawable? = null) {


        setCompoundDrawablesWithIntrinsicBounds(startOfTheText, topOfTheText, endOfTheText, bottomOfTheText)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                clearButtonEnd = (searchButton.intrinsicWidth + paddingStart).toFloat()
                when {
                    event.x < clearButtonEnd -> isClearButtonClicked = true
                }
            } else {
                clearButtonStart = (width - paddingEnd - searchButton.intrinsicWidth).toFloat()
                when {
                    event.x > clearButtonStart -> isClearButtonClicked = true
                }
            }

            if (isClearButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        searchButton = ContextCompat.getDrawable(context, R.drawable.close) as Drawable
                        showSearchButton()
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        searchButton = ContextCompat.getDrawable(context, R.drawable.close) as Drawable
                        when {
                            //do hire when button, search button on touch
                            text != null -> text?.clear()
                        }
                        hideSearchButton()
                        return true
                    }
                    else -> return false
                }
            } else return false
        }
        return false
    }

}