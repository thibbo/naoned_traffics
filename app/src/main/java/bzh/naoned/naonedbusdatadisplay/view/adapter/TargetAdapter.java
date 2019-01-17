package bzh.naoned.naonedbusdatadisplay.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import bzh.naoned.naonedbusdatadisplay.R;
import bzh.naoned.naonedbusdatadisplay.databinding.TargetListItemBinding;
import bzh.naoned.naonedbusdatadisplay.service.model.Target;

public class TargetAdapter extends RecyclerView.Adapter<TargetAdapter.TargetViewHolder> {
    private List<Target> targetsList;

    @NonNull
    @Override
    public TargetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TargetListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.target_list_item, viewGroup, false);

        return new TargetViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TargetViewHolder holder, int i) {
        holder.binding.setTarget(targetsList.get(i));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return targetsList == null ? 0 : targetsList.size();
    }

    public void setValues(List<Target> targets) {
        targetsList = targets;
        notifyItemRangeInserted(0, targets.size()
        );
    }

    static class TargetViewHolder extends RecyclerView.ViewHolder {

        final TargetListItemBinding binding;

        public TargetViewHolder(TargetListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
