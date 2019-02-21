package bzh.naoned.naonedbusdatadisplay

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData
import bzh.naoned.naonedbusdatadisplay.view.ui.TrafficFragment
import bzh.naoned.naonedbusdatadisplay.view.ui.TrafficListFragment

class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TrafficListFragment.newInstance())
                    .commitNow()
        }

        supportFragmentManager.addOnBackStackChangedListener(this)
        shouldDisplayHomeUp()
    }

    fun show(trafficData: TrafficData) {
        val trafficFragment = TrafficFragment.forTraffic(trafficData.id)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("traffic")
                .replace(R.id.container,
                        trafficFragment, null).commit()
    }

    override fun onBackStackChanged() {
        shouldDisplayHomeUp()
    }

    fun shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        val canGoBack = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(canGoBack)
    }

    override fun onSupportNavigateUp(): Boolean {
        //This method is called when the up button is pressed. Just the pop back stack.
        supportFragmentManager.popBackStack()
        return true
    }
}
