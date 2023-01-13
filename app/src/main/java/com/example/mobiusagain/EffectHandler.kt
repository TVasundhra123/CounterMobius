package com.example.mobiusagain

import io.reactivex.ObservableTransformer
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx2.RxMobius

object EffectHandler {
    fun createEffectHanler(
        viewEffectConsumer: Consumer<ViewEffects>
    ): ObservableTransformer<Effects, Events> {
        return RxMobius.subtypeEffectHandler<Effects,Events>()
            .addConsumer(
                PlusClickEffect::class.java
            ) {
                effect ->
                viewEffectConsumer.accept(RenderPlusClickEffect(
                    effect.counter
                ))
            }

            .addConsumer(
                MinusClickEffect::class.java
            ) {
                effect ->
                viewEffectConsumer.accept(RenderMinusClickEffect(
                    effect.counter
                ))
            }
            .addConsumer(
                BelowZeroEffect::class.java
            ) {
                viewEffectConsumer.accept(RenderBelowZeroEffect)
            }
            .build()
    }
}