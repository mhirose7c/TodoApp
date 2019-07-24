package com.example.todoapp.utility

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

class DateDialogFragment (var target: Button) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calender = Calendar.getInstance()
        return DatePickerDialog(
            target.context,
            theme,
            DatePickerDialog.OnDateSetListener{_, year, month, dayOfMonth ->
                val inputDate = Calendar.getInstance()
                inputDate.set(year, month, dayOfMonth)
                val dfInputDate = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
                val strInputDate = dfInputDate.format(inputDate.time)
                target.text = strInputDate
            },
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH))
            .also {
                val minDate = Calendar.getInstance()
                it.datePicker.minDate = minDate.timeInMillis
                it.setTitle("")
            }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        target.text = "指定なし"
    }
}