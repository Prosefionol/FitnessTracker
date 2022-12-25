package com.vk.fitnesstracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProgressAdapter(val listener: Listener, private val trainings: List<Training>) : RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_trainings, null)
        return ProgressViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        holder.bind(trainings[position], listener)
    }

    override fun getItemCount(): Int {
        return trainings.size
    }

    class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv1: TextView = itemView.findViewById(R.id.view_trainings_t1)
        private val tv2: TextView = itemView.findViewById(R.id.view_trainings_t2)
        private val tv3: TextView = itemView.findViewById(R.id.view_trainings_t3)

        fun bind(training: Training, listener: Listener) {
            tv1.text = "Тренировка ${training.num.toString()}"
            tv2.text = "Пройдена дистанция: ${training.dist.toString()}"
            tv3.text = "Потрачено каллорий: ${training.cal.toString()}"
            itemView.setOnClickListener {
                listener.onClick(training)
            }
        }
    }

    interface Listener {
        fun onClick(tr : Training)
    }

}