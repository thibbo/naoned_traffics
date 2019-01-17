package bzh.naoned.naonedbusdatadisplay;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData;
import bzh.naoned.naonedbusdatadisplay.view.ui.TrafficFragment;
import bzh.naoned.naonedbusdatadisplay.view.ui.TrafficListFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TrafficListFragment.newInstance())
                    .commitNow();
        }

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        shouldDisplayHomeUp();
    }

    public void show(TrafficData trafficData) {
        TrafficFragment trafficFragment = TrafficFragment.forTraffic(trafficData.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("traffic")
                .replace(R.id.container,
                        trafficFragment, null).commit();
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
