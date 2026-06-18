package com.example.test0914

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test0914.databinding.DialogDiaryBinding
import com.example.test0914.databinding.FragmentDiaryBinding
import java.util.Calendar

/**
 * [DIARY FRAGMENT] 일기장 화면
 * - DatePickerDialog: 날짜 선택
 * - TimePickerDialog: 시간 선택
 * - 커스텀 AlertDialog: 일기 내용 입력
 * - 내부 저장소: 파일 저장/읽기
 * - ViewBinding 사용
 */
class DiaryFragment: Fragment(R.layout.fragment_diary) {
    private var _binding: FragmentDiaryBinding? = null
    private val binding get() = _binding!!

    // [FILE] 선택된 날짜/시간 (파일명 생성에 사용)
    private var selectedDate = "diary"
    private var selectedTime = "00:00"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("Tag", "DiaryFragment")
        _binding = FragmentDiaryBinding.bind(view)

        // [DIALOG] [DATE] DatePickerDialog로 날짜 선택
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

        // [DIALOG] [TIME] TimePickerDialog로 시간 선택
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
                true // 24시간 모드
            ).show()
        }

        binding.btnDialog.setOnClickListener {
            val dialogBinding = DialogDiaryBinding.inflate(layoutInflater)

            // [DIALOG] [CUSTOM] 커스텀 레이아웃 AlertDialog로 일기 입력
            AlertDialog.Builder(requireContext())
                .setTitle("일기 입력")
                .setView(dialogBinding.root)  // 커스텀 레이아웃 연결
                .setPositiveButton("저장") { _, _ ->
                    val content = dialogBinding.etDialogMemo.text.toString()
                    val fileName = "$selectedDate.txt"
                    // [FILE_SAVE] 내부 저장소에 저장
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