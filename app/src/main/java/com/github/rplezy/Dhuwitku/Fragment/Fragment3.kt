package com.github.rplezy.Dhuwitku.Fragment



import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rplezy.Dhuwitku.Adapter.HistoryAdapter
import com.github.rplezy.Dhuwitku.Config.Service
import com.github.rplezy.Dhuwitku.Model.ItemHistory
import com.github.rplezy.Dhuwitku.Model.History
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class Fragment3 : Fragment() {
    private val TAG: String = QrGenerate::class.java.getName()
    private lateinit var data : SharedPreferences
    private var cache : Int? = null
    private var loader: LinearLayout? = null
    private var saldo : TextView?= null
    private var name : TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the dialog with custom view
        data = SharedPreferences(context!!)
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

        //getData()
        name = view.findViewById(R.id.tv_username)
        saldo = view.findViewById(R.id.tv_saldo)
        loader = view.findViewById(R.id.loading)
        //name!!.text = data.getString("USERNAME")
        loader!!.visibility = View.VISIBLE
        if(cache == 1){
            getData()
        }
        getHistory()

        return view
    }

    fun showqr() {


        var qrdat : String? = data.getString("ID_USER")
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
                    cache = 1
                    loader!!.visibility = View.GONE
                    saldo!!.text = response.body()!!.data!!.saldo!!
                    name!!.text = response.body()!!.data!!.username!!

                }
                else{
                    loader!!.visibility = View.GONE
                    Toast.makeText(activity!!,"Error Fetching", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun getHistory() {
        var qrdat: String? = data!!.getString("ID_USER")
        //var gg: Int = 1
        var tipe = "topup"
        val HistoryModel = Service.get().getHistory(
            qrdat.toString(),
            tipe
        )
        HistoryModel.enqueue(object : retrofit2.Callback<History> {
            override fun onFailure(call: Call<History>, t: Throwable) {
                loader!!.visibility = View.GONE
                Toast.makeText(activity!!, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<History>, response: Response<History>) {
                if (response.body()!!.status == true) {
                    // tv_nama.text = response.body()!!.deskripsi!!
                    loader!!.visibility = View.GONE
                    showData(response.body()?.data)

                } else {
                    loader!!.visibility = View.GONE
                    Toast.makeText(activity!!, "Error Fetching", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
    private fun showData(data: ArrayList<ItemHistory>?) {
        rv_history.apply {
            layoutManager = LinearLayoutManager(activity!!)
            adapter = HistoryAdapter(activity!!, data)
        }
    }
}

