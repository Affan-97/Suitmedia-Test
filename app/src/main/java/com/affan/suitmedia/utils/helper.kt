package com.affan.suitmedia.utils

import android.content.Context
import android.widget.Toast

fun palindromeChecker(inputStr: String): Boolean {
    val sb = StringBuilder(inputStr)


    val reverseStr = sb.reverse().toString()


    return inputStr.equals(reverseStr, ignoreCase = true)
}

fun showToast(context:Context,text:String){
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}