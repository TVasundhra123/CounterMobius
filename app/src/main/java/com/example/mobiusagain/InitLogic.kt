package com.example.mobiusagain

import com.spotify.mobius.First
import com.spotify.mobius.Init

internal class InitLogic: Init<Model, Effects> {
    override fun init(model: Model): First<Model, Effects> {
        return First.first(model)
    }
}