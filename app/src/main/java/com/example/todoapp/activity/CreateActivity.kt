package com.example.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.model.TaskModel
import com.example.todoapp.utility.DateDialogFragment
import io.realm.Realm
import java.util.*


class CreateActivity : AppCompatActivity() {
    private val inputMethodManager: InputMethodManager? = null
    private var mainLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create"
        mainLayout = this.findViewById<LinearLayout>(R.id.main_layout)

        var title = this.findViewById<EditText>(R.id.title)
        var detail = this.findViewById<EditText>(R.id.detail)

        var limitDate = this.findViewById<Button>(R.id.limit_date)
        limitDate.setOnClickListener {
            DateDialogFragment(limitDate).show(supportFragmentManager, limitDate::class.java.simpleName)
        }

        var createButton = this.findViewById<Button>(R.id.create_button)
        createButton.setOnClickListener {

            var realm = Realm.getDefaultInstance()
            realm.beginTransaction()

            var inputData = realm.createObject(TaskModel::class.java, UUID.randomUUID().toString())
            inputData.taskName = title.text.toString()
            inputData.detaile = detail.text.toString()
            inputData.targetDate = limitDate.text.toString()

            if (!inputData.isValid
                && !inputData.inputValid()){
                realm.cancelTransaction()
                realm.close()
                return@setOnClickListener
            }

            realm.copyToRealm(inputData)

            realm.commitTransaction()
            realm.close()

            // Main画面に戻る
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        inputMethodManager?.hideSoftInputFromWindow(mainLayout?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        mainLayout?.requestFocus()

        return false
    }
}