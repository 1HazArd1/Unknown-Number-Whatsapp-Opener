package com.aakash.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number ="0"
        /* gathering the number in the form of char sequence */
        if(intent.action== Intent.ACTION_PROCESS_TEXT){
            number=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }

        if (number.isDigitsOnly()){
           startWhatsapp(number)
        }
        else{
            Toast.makeText(this,"Enter Valid Input",Toast.LENGTH_SHORT).show()
        }

    }

    private fun startWhatsapp(number: String) {
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        //checking if the number is in the format of+91 98 72346578
        val num:String=if(number[0]=='+'){
            number.substring(1) //num will store a value starting from index 1
        }
        else if(number.length==10){
            "91"+number
        }
        else{
            number
        }
        intent.data= Uri.parse("http://wa.me/$num")//this id is for whatsapp API
        //checking if whatsapp is installed inside the device or not
        if(packageManager.resolveActivity(intent,0)!=null){
            startActivity(intent)
        }
        else{
            Toast.makeText(this,"Install Whatsapp First",Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}