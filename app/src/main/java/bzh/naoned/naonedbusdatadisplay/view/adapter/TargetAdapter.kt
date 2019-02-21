package bzh.naoned.naonedbusdatadisplay.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import bzh.naoned.naonedbusdatadisplay.R
import bzh.naoned.naonedbusdatadisplay.databinding.TargetListItemBinding
import bzh.naoned.naonedbusdatadisplay.service.model.Target

internal class TargetAdapter : RecyclerView.Adapter<TargetAdapter.TargetViewHolder>() {
    private var targetsList: List<Target>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TargetViewHolder {
        val binding = DataBindingUtil
                .inflate<TargetListItemBinding>(LayoutInflater.from(viewGroup.context), R.layout.target_list_item, viewGroup, false)

        return TargetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TargetViewHolder, i: Int) {
        holder.binding.setTarget(targetsList?.get(i))
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return targetsList?.size?:0
    }

    fun setValues(targets: List<Target>?) {
        targetsList = targets
        notifyItemRangeInserted(0, targets?.size?:0)
    }

    internal class TargetViewHolder(val binding: TargetListItemBinding) : RecyclerView.ViewHolder(binding.getRoot())
}
