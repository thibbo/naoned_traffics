package bzh.naoned.naonedbusdatadisplay.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import bzh.naoned.naonedbusdatadisplay.R
import bzh.naoned.naonedbusdatadisplay.databinding.TrafficListItemBinding
import bzh.naoned.naonedbusdatadisplay.view.callback.TrafficClickCallback

internal class TrafficAdapter(private val trafficClickListener: TrafficClickCallback) : RecyclerView.Adapter<TrafficAdapter.TrafficViewHolder>() {
    private var trafficList: List<bzh.naoned.naonedbusdatadisplay.service.model.TrafficData>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TrafficViewHolder {
        val binding = DataBindingUtil
                .inflate<TrafficListItemBinding>(LayoutInflater.from(viewGroup.context), R.layout.traffic_list_item, viewGroup, false)

        binding.callback = trafficClickListener

        return TrafficViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrafficViewHolder, i: Int) {
        holder.binding.traffic = trafficList?.get(i)

        val targetAdapter = TargetAdapter()
        holder.binding.recyclerTarget.adapter = targetAdapter
        targetAdapter.setValues(trafficList?.get(i)?.targets)

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return trafficList?.size?:0
    }

    fun setValues(trafficData: List<bzh.naoned.naonedbusdatadisplay.service.model.TrafficData>?) {
        trafficList = trafficData
        notifyItemRangeInserted(0, trafficData?.size?:0)
    }

    internal class TrafficViewHolder(val binding: TrafficListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
