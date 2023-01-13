package com.example.mobiusagain

sealed class Events

data class PlusClickEvent(val counter: Int): Events()

data class MinusClickEvent(val counter: Int): Events()