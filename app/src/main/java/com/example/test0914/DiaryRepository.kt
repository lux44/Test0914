package com.example.test0914

import android.content.Context
import java.io.File

object DiaryRepository {
    fun save(context: Context, fileName: String, content: String) {
        val file = File(context.filesDir, fileName)
        file.writeText(content)
    }

    fun load(context: Context, fileName: String): String {
        val file = File(context.filesDir, fileName)
        return if (file.exists()) file.readText() else ""
    }
}