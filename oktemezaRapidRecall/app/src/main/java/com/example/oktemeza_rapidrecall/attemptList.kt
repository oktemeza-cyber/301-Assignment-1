package com.example.oktemeza_rapidrecall
import androidx.compose.runtime.mutableStateListOf

class attemptList {

    private val _attempts= mutableStateListOf(
        Attempt(0, 0, 0.0f)
    )

    fun addAttempt(attempt: Attempt){
        _attempts.add(attempt)
    }



}