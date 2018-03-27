package com.lamour.akotlinprelude

/**
 * Created by lamour on 3/26/18.
 */
import android.widget.Button
import android.graphics.Color

 enum class ButtonState {
    unable,
    enable
}

/*
* configureState: would either change the state of a button from unable to enable vice-versa
*   @param state: enum with two cases unable, enable
*   @param colorId: background color id of the button, set to light gray as default
*   @param textColor: text color id of the button, set to black as default
* */

fun Button.configureState(state: ButtonState, colorId: Int = Color.LTGRAY, textColor: Int = Color.BLACK) = when(state) {
    ButtonState.enable -> {
        this.isClickable = true
        this.isEnabled = true
        //this.background.colorFilter = null
        this.setTextColor(textColor)
        this.setBackgroundColor(colorId)

    }
    ButtonState.unable -> {
        this.isClickable = false
        this.isEnabled = false
        this.setBackgroundColor(Color.LTGRAY)
        //  this.background.setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY)
    }
}
