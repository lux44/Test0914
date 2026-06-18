package com.example.test0914

import android.app.AlertDialog
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test0914.databinding.FragmentWidgetBinding

class WidgetFragment: Fragment(R.layout.fragment_widget) {
    private var _binding: FragmentWidgetBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentWidgetBinding.bind(view)

        binding.timePicker.setIs24HourView(true)

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            binding.tvSelectedDate.text = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            binding.tvSelectedTime.text = "%02d:%02d".format(hourOfDay, minute)
        }

        binding.btnStartChrono.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.start()
        }

        binding.btnStopChrono.setOnClickListener {
            binding.chronometer.stop()
        }

        binding.btnWidgetDialog.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("위젯 실습")
                .setMessage("CalendarView, TimePicker, Chronometer 확인")
                .setPositiveButton("확인", null)
                .show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}