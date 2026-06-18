package com.example.test0914

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * [GRAPHICS [CANVAS CUSTOM_VIEW] 커스텀 그래픽 뷰
 * - Canvas 기반 이미지 그리기 및 변환
 * - 기하학적 변환: rotate(), scale(), translate(), skew()
 * - 색상 효과: ColorMatrix (회색, 밝기)
 *
 * 사용법: XML에 <com.example.smartapplab.view.GraphicView ... /> 로 추가
 */
class GraphicView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
    ) : View(context, attrs) {

    // [BITMAP] 기본 이미지 로드 (res/drawable/sample_image.png 필요)
    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.sampel_image)

    // [CANVAS] 변환 파라미터
    var scaleFactor = 1f       // 확대/축소 비율
    var rotationDeg = 0f       // 회전 각도
    var dx = 0f                // X축 이동
    var dy = 0f                // Y축 이동
    var skewX = 0f             // X축 기울이기
    var gray = false           // 회색 효과 여부
    var brightness = 1f        // 밝기 (1.0 = 원본)

    /**
     * [BITMAP] 외부에서 이미지 변경
     */
    fun setBitmap(newBitmap: Bitmap) {
        bitmap = newBitmap
        invalidate()  // [CANVAS] 다시 그리기 요청
    }

    /**
     * [CANVAS] 실제 그리기
     * 순서: translate → rotate → scale → skew → drawBitmap
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // [COLOR_MATRIX] 색상 필터 생성
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            colorFilter = buildColorFilter()
        }

        // [CANVAS] 현재 상태 저장
        val save = canvas.save()

        // [CANVAS] [TRANSLATE] 화면 중심 + 이동값으로 평행이동
        canvas.translate(width / 2f + dx, height / 2f + dy)

        // [CANVAS] [ROTATE] 회전
        canvas.rotate(rotationDeg)

        // [CANVAS] [SCALE] 확대/축소
        canvas.scale(scaleFactor, scaleFactor)

        // [CANVAS] [SKEW] X축 기울이기
        canvas.skew(skewX, 0f)

        // [BITMAP] 이미지 그리기 (중심 기준)
        canvas.drawBitmap(
            bitmap,
            -bitmap.width / 2f,
            -bitmap.height / 2f,
            paint
        )

        // [CANVAS] 저장된 상태로 복원
        canvas.restoreToCount(save)
    }

    /**
     * [COLOR_MATRIX] 회색 + 밝기 효과 ColorFilter 생성
     */
    private fun buildColorFilter(): ColorFilter {
        val matrix = ColorMatrix()

        // [COLOR_MATRIX] [GRAY] 채도 0 = 회색
        if (gray) {
            matrix.setSaturation(0f)
        }

        // [COLOR_MATRIX] [BRIGHT] 밝기 행렬
        val brightnessMatrix = ColorMatrix(
            floatArrayOf(
                brightness, 0f, 0f, 0f, 0f,
                0f, brightness, 0f, 0f, 0f,
                0f, 0f, brightness, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            )
        )
        matrix.postConcat(brightnessMatrix)

        return ColorMatrixColorFilter(matrix)
    }
    }