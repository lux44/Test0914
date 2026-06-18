package com.example.test0914

import android.app.AlertDialog
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test0914.databinding.FragmentWidgetBinding

/**
 * [WIDGET FRAGMENT] 위젯 실습 화면
 * - CalendarView: 달력형 날짜 선택
 * - TimePicker: 시간 선택 (spinner 모드)
 * - Chronometer: 경과 시간 측정
 * - AlertDialog: 기본 다이얼로그
 * - ViewBinding 사용
 */
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

        // [DIALOG] 기본 AlertDialog

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