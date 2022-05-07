package com.example.notepad

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notepad.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var text: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFile()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menuOpen -> openFile()
            R.id.menuSave -> saveFile()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveFile() {
        try {
            text = binding.editTextTextMultiLine.text.toString()
            File(filesDir, "saveText").writeText(text)
            Toast.makeText(applicationContext, "Файл сохранён!}", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {}
    }

    private fun openFile() {
        try {
            text = File(filesDir, "saveText").readText()
            binding.editTextTextMultiLine.setText(text)
        } catch (e: Exception) {}
    }

    override fun onPause() {
        super.onPause()
        saveFile()
    }
}