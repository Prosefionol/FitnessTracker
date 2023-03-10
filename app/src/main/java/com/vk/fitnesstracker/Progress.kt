package com.vk.fitnesstracker

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class Progress : Fragment(), ProgressAdapter.Listener {

    companion object {
        fun newInstance() = Progress()
    }

    private lateinit var viewModel: ProgressViewModel
    private var cnt = 0
    private lateinit var loadingStub: ProgressBar
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProgressViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainings = mutableListOf<Training>()

        if (savedInstanceState != null) {
            cnt = savedInstanceState.getInt("counter", 0)
            generateTrainingList(cnt-1, trainings)
        }
        else
        {
            cnt = startTrainingList(trainings)
        }
        loadingStub = view.findViewById(R.id.progress_loadingStub)
        loadingStub.isVisible = true

        val rv: RecyclerView = view.findViewById(R.id.progress_rv)
        val adapter = ProgressAdapter(this, trainings)
        rv.adapter = adapter


        loadData {
            loadingStub.isVisible = it
            rv.layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", cnt)
    }

    private fun generateTrainingList(count: Int, trainings: MutableList<Training> ) {
        for(j in 0..count)
        {
            trainings.add(Training(j, j * 1.2, j*500))
        }
    }

    private fun startTrainingList(trainings: MutableList<Training>) : Int {
        for(j in 0..7)
        {
            trainings.add(Training(j, j * 1.2, j*500))
        }
        return 5
    }

    private fun loadData(callback: (Boolean) -> Unit) {
        thread {
            Thread.sleep(1000)
            handler.post {
                callback.invoke(false)
            }
        }
    }

    override fun onClick(tr: Training) {
        activity?.supportFragmentManager?.let {
            val transaction = it.beginTransaction()
            transaction.replace(R.id.frame_layout, createDetailedProgress(tr.num))
            transaction.commit()
        }
    }
}