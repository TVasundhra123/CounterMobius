package com.example.mobiusagain

sealed class ViewEffects

data class RenderPlusClickEffect(val counter: Int): ViewEffects()

data class RenderMinusClickEffect(val counter: Int): ViewEffects()

object RenderBelowZeroEffect: ViewEffects()
