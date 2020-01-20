package com.github.rplezy.Dhuwitku.Model

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(var context: Context) {
    private var preferences :  SharedPreferences
    private var editor : SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences("Dummy",0)
        editor = preferences.edit()
    }
    fun setString(key:String,value:String){
        editor.putString(key, value)
        editor.commit()
    }
    fun getString(key:String): String? {
        return preferences.getString(key,"")
    }
    fun session(Key: String, status: Boolean) {
        editor.putBoolean(Key, status!!)
        editor.commit()
    }
    fun getSession(Key:String): Boolean{
        return preferences.getBoolean(Key, false)
    }
    fun removeValue(KEY_NAME: String) {
        editor.remove(KEY_NAME)
        editor.commit()
    }
    fun clearSharedPreference() {

        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        editor.clear()
        editor.commit()
    }
}