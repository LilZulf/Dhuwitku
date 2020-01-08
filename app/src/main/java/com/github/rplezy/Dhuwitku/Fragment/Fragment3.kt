package com.github.rplezy.Dhuwitku.Fragment



import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.Fragment
import com.github.rplezy.Dhuwitku.QrGenerate
import com.github.rplezy.Dhuwitku.QrScanner
import com.github.rplezy.Dhuwitku.R
import com.github.rplezy.Dhuwitku.TopUp
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_qr_generate.*
import kotlinx.android.synthetic.main.activity_qr_generate.view.*
import kotlinx.android.synthetic.main.fragment3.view.*


/**
 * A simple [Fragment] subclass.
 */
class Fragment3 : Fragment() {
    private val TAG: String = QrGenerate::class.java.getName()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the dialog with custom view

        val view: View = inflater.inflate(R.layout.fragment3, container, false)

        view.btn_kirim.setOnClickListener {
            val intent = Intent(context, QrScanner::class.java)
            startActivity(intent)
        }
        view.btn_minta.setOnClickListener {
            showqr()
            view.qr.visibility = View.VISIBLE
        }
        view.qr.setOnClickListener{
            view.qr.visibility = View.GONE
        }
        view.btn_topup.setOnClickListener{
            val intent = Intent (context, TopUp::class.java)
            startActivity(intent)
        }

        return view
    }

    fun showqr() {
        var qrdata: String = "123"


//            edt_value.text.toString().trim()
//            if (edt_value.length() > 0) {
        val manager = activity!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val point = Point()
        display.getSize(point)
        val width = point.x
        val height = point.y
        var smallerDimension = if (width < height) width else height
        smallerDimension = smallerDimension * 3 / 4
        val qrgEncoder = QRGEncoder(
            qrdata, null,
            QRGContents.Type.TEXT,
            smallerDimension
        )
        try {
            val bitmap = qrgEncoder.encodeAsBitmap()
            qr_image.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Log.v(TAG, "error")
        }
//            } else {
//                edt_value.error = "Required"
//            }

    }
}

