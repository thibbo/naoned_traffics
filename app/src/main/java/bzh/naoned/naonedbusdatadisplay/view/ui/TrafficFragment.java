package bzh.naoned.naonedbusdatadisplay.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import bzh.naoned.naonedbusdatadisplay.R;
import bzh.naoned.naonedbusdatadisplay.databinding.FragmentTrafficBinding;
import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData;
import bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficViewModel;

public class TrafficFragment extends Fragment {
    private static final String KEY_TRAFFIC_ID = "id";
    private FragmentTrafficBinding binding;

    public static TrafficFragment newInstance() {
        return new TrafficFragment();
    }

    public static TrafficFragment forTraffic(String trafficId) {
        TrafficFragment fragment = TrafficFragment.newInstance();
        Bundle args = new Bundle();
        args.putString(KEY_TRAFFIC_ID, trafficId);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_traffic, container, false);
        binding.setIsLoading(true);

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TrafficViewModel trafficViewModel = ViewModelProviders.of(this).get(bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficViewModel.class);
        if (getArguments() != null) {
            TrafficData trafficData = trafficViewModel.getTraffic((String) getArguments().get(KEY_TRAFFIC_ID));
            binding.setTraffic(trafficData);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        // inflate menu resource file.
        menuInflater.inflate(R.menu.share_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_share) {
            shareTrafficData();
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareTrafficData() {
        TrafficData trafficData = binding.getTraffic();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, trafficData.getTitle());
        sendIntent.putExtra(Intent.EXTRA_TEXT, trafficData.getMessage());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
