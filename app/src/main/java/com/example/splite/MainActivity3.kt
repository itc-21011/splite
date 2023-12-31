package com.example.splite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var delete: ImageView
    private lateinit var edit: ImageView
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        name = findViewById(R.id.editTextPersonName)
        phone = findViewById(R.id.editTextPhone)
        delete = findViewById(R.id.imageView3)
        edit = findViewById(R.id.imageView4)

        db = DBHelper(this)

        name.setText(intent.getStringExtra("name"))
        phone.setText(intent.getStringExtra("phone"))

        delete.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val names = name.text.toString()
            val deletedata = db.deleteuserdata(names)

                if (deletedata==true){
                    Toast.makeText(this, "Delete Contact", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Not Delete", Toast.LENGTH_SHORT).show()
                }
            startActivity(intent)
        }

        edit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val names = name.text.toString()
            val numbers = phone.text.toString()
            val updatedata = db.updateuserdata(names, numbers)

            if (updatedata==true){
                Toast.makeText(this, "Update Contact", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Not Update", Toast.LENGTH_SHORT).show()
            }
            startActivity(intent)
        }
    }
}