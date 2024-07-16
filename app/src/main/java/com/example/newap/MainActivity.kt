package com.example.newap

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val recipes = arrayOf("Spaghetti Carbonara", "Chicken Curry", "Vegetable Stir-fry", "Pancakes")
    private val ingredients = arrayOf(
        "Pasta, Eggs, Bacon, Cheese, Pepper",
        "Chicken, Curry Powder, Coconut Milk, Vegetables, Rice",
        "Mixed Vegetables, Soy Sauce, Oil, Garlic",
        "Flour, Eggs, Milk, Sugar, Baking Powder"
    )
    private val instructions = arrayOf(
        "Cook pasta. Fry bacon. Mix eggs and cheese. Combine all.",
        "Cook chicken. Add curry powder. Stir in coconut milk and vegetables.",
        "Stir-fry vegetables with soy sauce and garlic.",
        "Mix ingredients. Cook on a hot griddle."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        val mySpinner: Spinner = findViewById(R.id.mySpinner)
        val myButton: Button = findViewById(R.id.myButton)
        val myTextView: TextView = findViewById(R.id.myTextView)

        // Spinner Adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, recipes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mySpinner.adapter = adapter

        // Button OnClickListener
        myButton.setOnClickListener {
            val position = mySpinner.selectedItemPosition
            if (position >= 0 && position < recipes.size) {
                val details = "Ingredients: ${ingredients[position]}\nInstructions: ${instructions[position]}"
                myTextView.text = details
                Toast.makeText(this, "Recipe Loaded", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a recipe", Toast.LENGTH_SHORT).show()
            }
        }

        // Spinner OnItemSelectedListener
        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Optional: Do something when the selection changes
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }
}
