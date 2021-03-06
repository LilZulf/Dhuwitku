package com.github.rplezy.Dhuwitku

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class QrScanner : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) {
        // Do something with the result here
        // Log.v("tag", rawResult.getText()); // Prints scan results
        // Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        Log.v("TAG", rawResult.text) // Prints scan results

        Log.v("TAG", rawResult.barcodeFormat.toString())
        val builder =
            AlertDialog.Builder(this)
        builder.setTitle("Scan Result")
        builder.setMessage(rawResult.text)
        val alert1 = builder.create()
        //alert1.show()
        val i = Intent(this@QrScanner,GiftActivity::class.java)
        i.putExtra("Tujuan", rawResult.text)
        startActivity(i)
        //mScannerView!!.resumeCameraPreview(this)

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}