package com.example.pocketcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // lateinit defines a promise to initialize later
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // initializes as an empty list
        itemAdapter = ItemAdapter(mutableListOf())

        rvGroceryList.adapter = itemAdapter
        rvGroceryList.layoutManager = LinearLayoutManager(this)

        //give functionality to our buttons
        btnAdd.setOnClickListener {
            val itemTitle = etItemTitle.text.toString()
            // Do not add if empty
            if(itemTitle.isNotEmpty()) {
                val item = Item(itemTitle)
                itemAdapter.addItem(item)
                // clear input after entering
                etItemTitle.text.clear()
            }
        }
        btnDelete.setOnClickListener {
            itemAdapter.deleteCheckedItems()
        }
    }
}