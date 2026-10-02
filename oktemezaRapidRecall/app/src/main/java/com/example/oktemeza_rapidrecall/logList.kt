package com.example.oktemeza_rapidrecall
import androidx.compose.runtime.mutableStateListOf

class logList {

    private val _logs= mutableStateListOf(
        Log("0", "0", "0", true, 0)

    )




    fun addLog(log: Log){
        _logs.add(log)
    }



}