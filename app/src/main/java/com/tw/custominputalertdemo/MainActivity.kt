package com.tw.custominputalertdemo

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper

class MainActivity : AppCompatActivity() {

    lateinit var btnOpenDialog: Button
    lateinit var tvData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenDialog = findViewById(R.id.btnOpenDialog)
        tvData = findViewById(R.id.tvData)

        btnOpenDialog.setOnClickListener {
            openInputDialog()
        }
    }

    private fun openInputDialog() {

        val dialog = Dialog( ContextThemeWrapper(this@MainActivity, R.style.AlertDialogCustom ))
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.input_dialog_screen)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowlp = dialog.window?.attributes
        windowlp?.gravity = Gravity.BOTTOM
        dialog.window?.attributes = windowlp

        val tvSave: TextView = dialog.findViewById(R.id.tvSave)
        val tvCancel: TextView = dialog.findViewById(R.id.tvCancel)
        val etInputText: EditText = dialog.findViewById(R.id.etInputText)

        tvSave.setOnClickListener {

            if (etInputText.text.toString()=="Hello World!!"){
                etInputText.error = resources.getString(R.string.enter_updated_data)
            }else{
                dialog.dismiss()
                if (etInputText.text.toString().isEmpty()){
                    Toast.makeText(this@MainActivity, "input can not be empty", Toast.LENGTH_SHORT).show()
                }else{
                    // do your action with input data
                    tvData.setText(etInputText.text.toString())
                    Toast.makeText(this@MainActivity, "Subscribe to ${etInputText.text.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tvCancel.setOnClickListener { dialog.dismiss() }

        if (!dialog.isShowing) { dialog.show() }
    }
}