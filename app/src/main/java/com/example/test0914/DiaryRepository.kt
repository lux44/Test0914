package com.example.test0914

import android.content.Context
import java.io.File

/**
 * [FILE [REPOSITORY 내부 저장소 파일 저장/읽기 유틸
 * - filesDir 경로에 텍스트 파일 저장
 * - writeText() / readText() 사용
 * - 날짜별 파일명으로 관리
 */
object DiaryRepository {

    /**
     * [FILE_SAVE 파일 저장
     * @param context Context
     * @param fileName 저장할 파일명 (예: "2025-01-01.txt")
     * @param content 저장할 내용
     */
    fun save(context: Context, fileName: String, content: String) {
        val file = File(context.filesDir, fileName)
        file.writeText(content)
    }

    /**
     * [FILE_LOAD 파일 읽기
     * @param context Context
     * @param fileName 읽을 파일명
     * @return 파일 내용 (없으면 빈 문자열)
     */
    fun load(context: Context, fileName: String): String {
        val file = File(context.filesDir, fileName)
        return if (file.exists()) file.readText() else ""
    }
}