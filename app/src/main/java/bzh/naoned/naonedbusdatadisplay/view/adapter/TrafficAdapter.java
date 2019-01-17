package bzh.naoned.naonedbusdatadisplay.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import bzh.naoned.naonedbusdatadisplay.R;
import bzh.naoned.naonedbusdatadisplay.databinding.TrafficListItemBinding;
import bzh.naoned.naonedbusdatadisplay.view.callback.TrafficClickCallback;

public class TrafficAdapter extends RecyclerView.Adapter<TrafficAdapter.TrafficViewHolder> {
    private final TrafficClickCallback trafficClickListener;
    private List<bzh.naoned.naonedbusdatadisplay.service.model.TrafficData> trafficList;

    public TrafficAdapter(TrafficClickCallback clickListener) {
        this.trafficClickListener = clickListener;
    }

    @NonNull
    @Override
    public TrafficViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TrafficListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.traffic_list_item, viewGroup, false);

        binding.setCallback(trafficClickListener);

        return new TrafficViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficViewHolder holder, int i) {
        holder.binding.setTraffic(trafficList.get(i));

        TargetAdapter targetAdapter = new TargetAdapter();
        holder.binding.recyclerTarget.setAdapter(targetAdapter);
        targetAdapter.setValues(trafficList.get(i).getTargets());

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return trafficList == null ? 0 : trafficList.size();
    }

    public void setValues(List<bzh.naoned.naonedbusdatadisplay.service.model.TrafficData> trafficData) {
        trafficList = trafficData;
        notifyItemRangeInserted(0, trafficData.size()
        );
    }

    static class TrafficViewHolder extends RecyclerView.ViewHolder {

        final TrafficListItemBinding binding;

        public TrafficViewHolder(TrafficListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
