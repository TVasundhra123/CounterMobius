package com.example.mobiusagain

class ViewRenderer(private val viewCallback: ViewCallBack) {
    fun updateView(viewEffects: ViewEffects) {
        when(viewEffects) {
            is RenderPlusClickEffect -> viewCallback.showCounterValue(viewEffects.counter)

            is RenderMinusClickEffect -> viewCallback.showCounterValue(viewEffects.counter)

            is RenderBelowZeroEffect -> viewCallback.showBelowZeroToast()
        }
    }
}