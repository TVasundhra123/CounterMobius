package com.example.mobiusagain

import com.spotify.mobius.Effects.effects
import com.spotify.mobius.Next
import com.spotify.mobius.Update

internal class Logic: Update<Model,Events,Effects> {
    override fun update(
        model: Model,
        event: Events
    ): Next<Model, Effects> {

        return when(event) {
            is PlusClickEvent -> {
                val updatedModel = model.updateCounterState(event.counter+1)
                Next.next(updatedModel, effects(
                    PlusClickEffect(updatedModel.counter)
                ))
            }

            is MinusClickEvent -> {
                if(event.counter == 0) {
                    Next.dispatch(effects(
                        BelowZeroEffect
                    ))
                }
                else
                {
                    val updatedModel = model.updateCounterState(event.counter-1)
                    Next.next(updatedModel , effects(
                        MinusClickEffect(updatedModel.counter)
                    ))
                }
            }
        }
    }
}