package ir.reza_mahmoudi.newsgram.core.util.log

import android.util.Log
import ir.reza_mahmoudi.newsgram.BuildConfig


fun showLog(tag: String, msg: String){
    if(BuildConfig.DEBUG){
        Log.e(tag,msg)
    }
}
fun showLog(msg: String){
    if(BuildConfig.DEBUG){
        Log.e("Test Log: ",msg)
    }
}