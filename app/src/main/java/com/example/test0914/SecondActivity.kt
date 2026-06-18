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
    /**
     * [INTENT [RESULT 결과 반환형 Activity
     * - 명시적 Intent로 호출됨
     * - 전달받은 메시지 표시
     * - 사용자 입력을 결과로 돌려줌
     */

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