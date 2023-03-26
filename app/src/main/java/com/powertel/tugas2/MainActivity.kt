package com.powertel.tugas2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.powertel.tugas2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var countDownText: TextView
    private lateinit var startButton: Button
    private lateinit var inputEditText: EditText
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var stopButton: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        countDownText = findViewById(R.id.countdown_text);
        startButton = findViewById(R.id.start_button);
        inputEditText = findViewById(R.id.input_edittext);
        stopButton = findViewById(R.id.stop_button)

        startButton.setOnClickListener {
            val input = inputEditText.text.toString().toLong() * 1000
//            convert ke detik
            startCountdown(input)
        }
        stopButton.setOnClickListener {
            stopService(Intent(this@MainActivity,MyService::class.java))
//            perintah untuk menjalankan stop service
        }
    }

    private fun startCountdown(time: Long) {
        countDownTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                countDownText.text = "Time remaining: $secondsLeft seconds"

            }

            override fun onFinish() {
                startService(Intent(this@MainActivity,MyService::class.java))
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }

    }
}