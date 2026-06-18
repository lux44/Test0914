package com.example.test0914

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.test0914.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NavController 가져오기
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        // BottomNavigationView와 NavController 연결 (이 한 줄이 핵심!)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "MainActivity onResume")
    }

    override fun onPause() {
        Log.d("Lifecycle", "MainActivity onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("Lifecycle", "MainActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("Lifecycle", "MainActivity onDestroy")
        super.onDestroy()
    }

}