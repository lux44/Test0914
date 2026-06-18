package com.example.test0914

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test0914.databinding.FragmentDiaryBinding
import java.util.Calendar

class DiaryFragment: Fragment(R.layout.fragment_diary) {
    private var _binding: FragmentDiaryBinding? = null
    private val binding get() = _binding!!

    private var selectedDate = "diary"
    private var selectedTime = "00:00"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDiaryBinding.bind(view)

        binding.btnDate.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    selectedDate = "%04d-%02d-%02d".format(year, month + 1, day)
                    binding.tvDate.text = selectedDate
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnTime.setOnClickListener {
            val cal = Calendar.getInstance()
            TimePickerDialog(
                requireContext(),
                { _, hour, minute ->
                    selectedTime = "%02d:%02d".format(hour, minute)
                    binding.tvTime.text = selectedTime
                },
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        binding.btnDialog.setOnClickListener {
            val dialogBinding = DialogDiaryBinding.inflate(layoutInflater)

            AlertDialog.Builder(requireContext())
                .setTitle("일기 입력")
                .setView(dialogBinding.root)
                .setPositiveButton("저장") { _, _ ->
                    val content = dialogBinding.etDialogMemo.text.toString()
                    val fileName = "$selectedDate.txt"
                    DiaryRepository.save(
                        requireContext(),
                        fileName,
                        "[$selectedDate $selectedTime]\n$content"
                    )
                    binding.tvResult.text = "저장 완료: $fileName"
                }
                .setNegativeButton("취소", null)
                .show()
        }

        binding.btnLoad.setOnClickListener {
            val fileName = "$selectedDate.txt"
            val content = DiaryRepository.load(requireContext(), fileName)
            binding.etDiary.setText(content.ifBlank { "저장된 내용이 없습니다." })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}