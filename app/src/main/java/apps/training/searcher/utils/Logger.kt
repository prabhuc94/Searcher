package apps.training.searcher.utils

import android.app.Activity
import android.content.Context
import android.util.Log

object Logger {

    fun error(tag : Any?, msg : Any?, e : Throwable ?= null){
        Log.e("$tag", "$msg", e)
    }

    fun Context.error(msg : Any?, e : Throwable ?= null){
        Log.e(this.packageName,"$msg",e)
    }

    fun Activity.error(msg : Any?, e : Throwable ?= null){
        Log.e(this.javaClass.name,"$msg",e)
    }

    fun debug(tag : Any?, msg : Any?, e : Throwable ?= null){
        Log.d("$tag", "$msg", e)
    }

    fun Context.debug(msg : Any?, e : Throwable ?= null){
        Log.d(this.packageName,"$msg",e)
    }

    fun Activity.debug(msg : Any?, e : Throwable ?= null){
        Log.d(this.javaClass.name,"$msg",e)
    }
}