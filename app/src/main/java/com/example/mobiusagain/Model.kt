package com.example.mobiusagain

data class Model(val counter: Int) {
    fun updateCounterState(counter: Int): Model{
        return copy(counter=counter)
    }
    companion object {
        fun initialModel() = Model(2)
    }
}