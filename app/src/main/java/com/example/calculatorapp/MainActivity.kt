package com.example.calculatorapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textinput : TextView
    // to check the last number is digit or not
    var lastNumeric : Boolean = false
    // to check the point ..
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // to set the status bar clolor ..
        window.statusBarColor = Color.parseColor("#019267")

        // Main code start..

        textinput = findViewById(R.id.textInput)




    } // onCreate class close


    // *** here present all the function


    // onDigit function
        fun onDigit(view : View)
        {
            textinput?.append((view as TextView).text)
            lastNumeric = true
        }
    // onClear function
    fun onClear(view : View)
    {
        textinput?.text=""
        lastNumeric = false
        lastDot = false
    }

    // onOperator function
    fun onOperator(view : View)
    {
        textinput?.text.let {
            if (lastNumeric or !isOperatorAdded(it.toString())) {
                textinput?.append((view as TextView).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    // onDecimalPoint function
    fun onDecimalPoint(view : View)
    {
        if(lastNumeric && !lastDot)
        {
            textinput?.append(".")
            lastNumeric = false
            lastDot = true

        }

    }

    // onEqual function
     fun onEqual(view : View)
    {
         if(lastNumeric) {
             var prefix = ""
             var textvalue = textinput?.text.toString()

             try {
                 if (textvalue.startsWith("-")) {
                     prefix = "-"
                     textvalue = textvalue.substring(1)
                 }


                 when {
                     textvalue.contains("/") -> {
                         val spliteValue = textvalue.split("/")

                         var one = spliteValue[0]
                         var two = spliteValue[1]

                         if (prefix.isNotEmpty()) {
                             one = prefix + one
                         }


                         textinput?.text = (one.toDouble() / two.toDouble()).toString()
                     }

                     textvalue.contains("*") -> {
                         val spliteValue = textvalue.split("*")

                         var one = spliteValue[0]
                         var two = spliteValue[1]

                         if (prefix.isNotEmpty()) {
                             one = prefix + one
                         }


                         textinput?.text = (one.toInt() * two.toInt()).toString()
                     }

                     textvalue.contains("-") -> {
                         val spliteValue = textvalue.split("-")

                         var one = spliteValue[0]
                         var two = spliteValue[1]

                         if (prefix.isNotEmpty()) {
                             one = prefix + one
                         }



                         textinput?.text = (one.toInt() - two.toInt()).toString()
                     }

                     textvalue.contains("+") -> {
                         val spliteValue = textvalue.split("+")

                         var one = spliteValue[0]
                         var two = spliteValue[1]

                         if (prefix.isNotEmpty()) {
                             one = prefix + one
                         }

                         textinput?.text = (one.toInt() + two.toInt()).toString()
                     }

                 }
             } // try
             catch (e: Exception) {
                 Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
             }
         } // if close
     }


    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")
        }
    }







} // AppCompatActivity class close