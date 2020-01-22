package com.github.rplezy.Dhuwitku

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_qr_generate.*


class QrGenerate : AppCompatActivity() {
    private val TAG: String = QrGenerate::class.java.getName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_generate)

//        start.setOnClickListener {
//            edt_value.text.toString().trim()
//            if (edt_value.length() > 0) {
//                val manager =
//                    getSystemService(Context.WINDOW_SERVICE) as WindowManager
//                val display = manager.defaultDisplay
//                val point = Point()
//                display.getSize(point)
//                val width = point.x
//                val height = point.y
//                var smallerDimension = if (width < height) width else height
//                smallerDimension = smallerDimension * 3 / 4
//                val qrgEncoder = QRGEncoder(
//                    edt_value.text.toString(), null,
//                    QRGContents.Type.TEXT,
//                    smallerDimension
//                )
//                try {
//                    val bitmap = qrgEncoder.encodeAsBitmap()
//                    qr_image.setImageBitmap(bitmap)
//                } catch (e: WriterException) {
//                    Log.v(TAG, "error")
//                }
//            } else {
//                edt_value.error = "Required"
//            }
//
//        }

    }
}
