package com.vk.fitnesstracker

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class Main : Fragment() {

    companion object {
        fun newInstance() = Main()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var mapview:MapView
    var state: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MapKitFactory.setApiKey(resources.getString(R.string.yandex_key))
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        MapKitFactory.initialize(view.context)
        mapview = view.findViewById<MapView>(R.id.ya_map)
        mapview.map.move(CameraPosition(Point(55.797198, 37.537772), 15.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5.0f), null)
        var button: Button = view.findViewById(R.id.button)
        button.text = "Начать тренировку"
        button.setOnClickListener{
            if (state == 0) {
                Toast.makeText(context, "Тренировка начата", Toast.LENGTH_SHORT).show()
                state = 1
                button.text = "Закончить тренировку"
            }
            else {
                Toast.makeText(context, "Тренировка закончена", Toast.LENGTH_SHORT).show()
                state = 0
                button.text = "Начать тренировку"
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        mapview.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapview.onStop()
        MapKitFactory.getInstance().onStop()
    }

}