package com.example.test0914

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test0914.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvReceived.text = intent.getStringExtra("msg") ?: "전달된 값 없음"

        binding.btnReturn.setOnClickListener {
            val resultIntent = Intent().putExtra("result", binding.etReply.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}