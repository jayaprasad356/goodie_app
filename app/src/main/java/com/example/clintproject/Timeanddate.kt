package com.example.clintproject

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.TimePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import android.widget.WrapperListAdapter
import kotlinx.android.synthetic.main.activity_timeanddate.*
import java.util.*

class Timeanddate : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeanddate)
        updownarrow.setOnClickListener{
            val param = pickuplocation.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0,440,0,0)
            pickuplocation.layoutParams = param

            val up = droplocation.layoutParams as ViewGroup.MarginLayoutParams
            up.setMargins(0,0,0,50)
            droplocation.layoutParams = up
            updownarrow.visibility=View.GONE
            newupdownarrow.visibility=View.VISIBLE
        }
        newupdownarrow.setOnClickListener{
            val param = pickuplocation.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0,0,0,0)
            pickuplocation.layoutParams = param

            val up = droplocation.layoutParams as ViewGroup.MarginLayoutParams
            up.setMargins(0,440,0,0)
            droplocation.layoutParams = up
            updownarrow.visibility=View.VISIBLE
            newupdownarrow.visibility=View.GONE
        }

        timebtn.setOnClickListener {
            calendarView.visibility=View.GONE
            datee.setTextColor(Color.BLACK)
            datebtn.setBackgroundResource(R.drawable.ic_btnwhiteshape)
timebtn.setBackgroundResource(R.drawable.ic_btnshape)
timee.setTextColor(Color.WHITE)
           timebox.visibility=View.VISIBLE
            showwatch()
        }
        datee.setOnClickListener {
            calendarView.visibility=View.VISIBLE

            datee.setTextColor(Color.WHITE)
            datebtn.setBackgroundResource(R.drawable.ic_btnshape)
            timebtn.setBackgroundResource(R.drawable.ic_btnwhiteshape)
            timee.setTextColor(Color.BLACK)
        }

    }

    private fun showwatch() {
        val currentTime=Calendar.getInstance()
        val currentHour=currentTime.get(Calendar.HOUR_OF_DAY)
        val currentMin=currentTime.get(Calendar.MINUTE)
        TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
       hour.setText("$hourOfDay : $minute")
      if (hourOfDay>12){
          pm.visibility=View.VISIBLE
      }else if (hourOfDay<12){
          am.visibility=View.VISIBLE
      }
        },currentHour,currentMin,false).show()
    }
}