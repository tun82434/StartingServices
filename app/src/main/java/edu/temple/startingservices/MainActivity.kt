package edu.temple.startingservices

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countdownInput: EditText = findViewById(R.id.countdownInput)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            val value = countdownInput.text.toString().toIntOrNull()

            value?.let {
                val launchIntent = Intent(this, CountdownService::class.java)
                launchIntent.putExtra("countdown_value", it)
                startService(launchIntent)
            }
        }

    }
}