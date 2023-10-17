package com.example.splite

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: FloatingActionButton
    lateinit var dbh: DBHelper
    private lateinit var newArray: ArrayList<Datalist>
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        button = findViewById(R.id.floatingActionButton)

        button.setOnClickListener{
            intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        dbh = DBHelper(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        displayuser()
    }

    private fun displayuser() {
        val newcursor: Cursor? = dbh.gettext()
        newArray = ArrayList<Datalist>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            val unumber = newcursor.getString(1)
            newArray.add(Datalist(uname, unumber))
        }
        adapter = MyAdapter(newArray)
        recyclerView.adapter = adapter
        adapter.onItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, MainActivity3::class.java)
                intent.putExtra("name", newArray[position].name)
                intent.putExtra("phone", newArray[position].contact)
                startActivity(intent)
            }
        })
    }
}