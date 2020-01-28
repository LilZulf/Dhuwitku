package com.github.rplezy.Dhuwitku.Fragment



import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.Fragment
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.SharedPreferences
import com.github.rplezy.Dhuwitku.Model.UserModel
import com.github.rplezy.Dhuwitku.QrGenerate
import com.github.rplezy.Dhuwitku.QrScanner
import com.github.rplezy.Dhuwitku.R
import com.github.rplezy.Dhuwitku.TopUp
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.activity_qr_generate.*
import kotlinx.android.synthetic.main.activity_qr_generate.view.*
import kotlinx.android.synthetic.main.fragment3.*
import kotlinx.android.synthetic.main.fragment3.view.*
import kotlinx.android.synthetic.main.rv_loading.*
import kotlinx.android.synthetic.main.rv_loading.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class Fragment3 : Fragment() {
    private val TAG: String = QrGenerate::class.java.getName()
    private var data : SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the dialog with custom view
        data = SharedPreferences(activity!!)
        val view: View = inflater.inflate(R.layout.fragment3, container, false)

        view.btn_kirim.setOnClickListener {
            val daf = Intent(context, QrScanner::class.java)
            startActivity(daf)
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
        view.loading.visibility = View.VISIBLE
        getData()
        return view
    }

    fun showqr() {


        var qrdat : String? = data!!.getString("ID_USER")
        val manager = activity!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val point = Point()
        display.getSize(point)
        val width = point.x
        val height = point.y
        var smallerDimension = if (width < height) width else height
        smallerDimension = smallerDimension * 3 / 4
        val qrgEncoder = QRGEncoder(
            qrdat, null,
            QRGContents.Type.TEXT,
            smallerDimension
        )
        try {
            val bitmap = qrgEncoder.encodeAsBitmap()
            qr_image.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Log.v(TAG, "error")
        }

    }
    private fun getData(){


        var qrdat : String? = data!!.getString("ID_USER")
        var getAPI = Service.get().getById(
            qrdat.toString()
        )

        getAPI.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                //view!!.loading.visibility = View.GONE
                Toast.makeText(activity!!,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if(response.body()!!.message == "Succes Fetching data"){
                   view!!.loading.visibility = View.GONE
                    tv_saldo.text = response.body()!!.data!!.saldo!!
                }
                else{
                    view!!.loading.visibility = View.GONE
                    Toast.makeText(activity!!,"Error Fetching", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }
}

