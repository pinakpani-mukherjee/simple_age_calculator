package com.example.application_1

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonclick = findViewById<Button>(R.id.buttonCalculate)
        buttonclick.setOnClickListener{view->
            clickDatePicker(view)
        }

    }
    fun clickDatePicker(view: View){
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,selectedDay ->
            val selectedDate = "$selectedYear/${selectedMonth+1}/$selectedDay"
            Toast.makeText(this,"You have chosen $selectedDate",Toast.LENGTH_SHORT).show()
            val changetext = findViewById<TextView>(R.id.tvSelectedDate)
            changetext.setText(selectedDate)
            val sdf = SimpleDateFormat("yyyy/MM/dd",Locale.JAPAN)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time/60000
            val differenceInMinutes = currentDateToMinutes-selectedDateInMinutes
            val changedatemins = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
            changedatemins.setText(differenceInMinutes.toString())

        }
            ,year,month,day)
        dpd.datePicker.setMaxDate(Date().time- 86400000)
        dpd.show()
    }
}