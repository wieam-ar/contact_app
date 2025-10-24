package com.example.contact_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val contacts = listOf(
            Contact("Alice", "+212600000001"),
            Contact("Bob", "+212600000002"),
            Contact("Charlie", "+212600000003"),
            Contact("Dina", "+212600000004"),
            Contact("Emma", "+212600000005")
        )

        recyclerView.adapter = ContactAdapter(contacts)
    }
}
