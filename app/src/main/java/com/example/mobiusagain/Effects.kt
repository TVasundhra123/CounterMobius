package com.example.mobiusagain

sealed class Effects

data class PlusClickEffect(val counter: Int): Effects()

data class MinusClickEffect(val counter: Int): Effects()

object BelowZeroEffect: Effects()