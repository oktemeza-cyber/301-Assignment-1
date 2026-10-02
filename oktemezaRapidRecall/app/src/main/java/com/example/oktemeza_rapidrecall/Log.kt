package com.example.oktemeza_rapidrecall

 data class Log (
    val length: String,
    val userInput: String,
    val targetSequence: String,
    val correctness: Boolean,
    val timeStamp: Int
)