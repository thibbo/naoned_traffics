package bzh.naoned.naonedbusdatadisplay.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bzh.naoned.naonedbusdatadisplay.MainActivity;
import bzh.naoned.naonedbusdatadisplay.R;
import bzh.naoned.naonedbusdatadisplay.databinding.FragmentTrafficListBinding;
import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData;
import bzh.naoned.naonedbusdatadisplay.view.adapter.TrafficAdapter;
import bzh.naoned.naonedbusdatadisplay.view.callback.TrafficClickCallback;
import bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficListViewModel;

public class TrafficListFragment extends Fragment {
    private bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficListViewModel trafficListViewModel;
    private TrafficAdapter trafficAdapter;
    private FragmentTrafficListBinding binding;
    private TrafficClickCallback trafficClickCallback = new TrafficClickCallback() {
        @Override
        public void onClick(bzh.naoned.naonedbusdatadisplay.service.model.TrafficData trafficData) {
            ((MainActivity) getActivity()).show(trafficData);
        }
    };

    public static TrafficListFragment newInstance() {
        return new TrafficListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_traffic_list, container, false);

        trafficAdapter = new TrafficAdapter(trafficClickCallback);
        binding.recyclerTraffic.setAdapter(trafficAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        trafficListViewModel = ViewModelProviders.of(this).get(bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficListViewModel.class);
        trafficListViewModel.init();

        observeViewModel(trafficListViewModel);
    }

    private void observeViewModel(TrafficListViewModel viewModel) {
        // define observer to update ui when data change
        viewModel.getTrafficListObservable().observe(this, new Observer<List<TrafficData>>() {
            @Override
            public void onChanged(@Nullable List<TrafficData> trafficData) {
                // update ui
                if (trafficData != null) {
                    binding.setIsLoading(false);
                    trafficAdapter.setValues(trafficData);
                }
            }
        });

    }
}
