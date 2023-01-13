package com.example.mobiusagain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mobiusagain.databinding.ActivityMainBinding
import com.spotify.mobius.Mobius
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusLoopViewModel
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx2.RxConnectables
import io.reactivex.ObservableTransformer

class MainActivity : AppCompatActivity(),ViewCallBack {
   private lateinit var binding: ActivityMainBinding
   private lateinit var viewModel: MobiusLoopViewModel<Model,Events,Effects,ViewEffects>

   private val render by lazy {
       ViewRenderer(this)
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = MobiusLoopViewModel.create(
            {
                consumer: Consumer<ViewEffects> ->
                Mobius.loop(Logic(), RxConnectables.fromTransformer(effectHandler(consumer)))
            },
            Model.initialModel(),
            {
                InitLogic().init(it)
            }
        )


        binding.plusButton.setOnClickListener {
            viewModel.dispatchEvent(PlusClickEvent(viewModel.model.counter))
        }

        binding.minusButton.setOnClickListener {
           viewModel.dispatchEvent(MinusClickEvent(viewModel.model.counter))
        }

        binding.counter.text = viewModel.model.counter.toString()

        viewModel.viewEffects.setObserver(
            this
        ) {
            render(it)
        }
    }

    private fun render(viewEffects: ViewEffects) {
        render.updateView(viewEffects)
    }

    private fun effectHandler(
        consumer: Consumer<ViewEffects>
    ): ObservableTransformer<Effects, Events> =
        EffectHandler.createEffectHanler(
            viewEffectConsumer = consumer
        )

    override fun showCounterValue(counter: Int) {
       binding.counter.text = counter.toString()
    }

    override fun showBelowZeroToast() {
       Toast.makeText(applicationContext,"Don't reduce below Zero, What will you do with negative numbers afterall ", Toast.LENGTH_SHORT).show()
    }

}