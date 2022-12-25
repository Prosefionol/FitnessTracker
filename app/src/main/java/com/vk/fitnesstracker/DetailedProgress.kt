package com.vk.fitnesstracker

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

fun createDetailedProgress(
    field: Int
): DetailedProgress {
    return DetailedProgress().apply {
        arguments = Bundle().apply { putInt("number", field) }
    }
}

class DetailedProgress : Fragment() {

    companion object {
        fun newInstance() = DetailedProgress()
    }

    private lateinit var viewModel: DetailedProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailed_progress, null, false)
        var cnt: Int? = arguments?.getInt("number", 0)
        if (cnt == null)
        {
            cnt = 0
        }
        view.findViewById<TextView>(R.id.detailed_trainings_t1).text = "Тренировка $cnt"
        view.findViewById<TextView>(R.id.detailed_trainings_t2).text = "Пройдена дистанция: ${cnt * 1.2}"
        view.findViewById<TextView>(R.id.detailed_trainings_t3).text = "Потрачено каллорий: ${cnt * 500}"
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailedProgressViewModel::class.java)
    }

}