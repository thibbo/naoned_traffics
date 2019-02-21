package bzh.naoned.naonedbusdatadisplay.view.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import bzh.naoned.naonedbusdatadisplay.R
import bzh.naoned.naonedbusdatadisplay.databinding.FragmentTrafficBinding

public class TrafficFragment : Fragment() {
    private lateinit var binding: FragmentTrafficBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_traffic, container, false)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val trafficViewModel = ViewModelProviders.of(this).get(bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficViewModel::class.java)
            val trafficData = trafficViewModel.getTraffic(arguments?.get(KEY_TRAFFIC_ID) as String)
            binding.traffic = trafficData
    }

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, menuInflater)
        // inflate menu resource file.
        menuInflater!!.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.menu_item_share) {
            shareTrafficData()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun shareTrafficData() {
        val trafficData = binding.traffic

        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, trafficData?.title)
        sendIntent.putExtra(Intent.EXTRA_TEXT, trafficData?.message)
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    companion object {
        private val KEY_TRAFFIC_ID = "id"

        fun newInstance(): TrafficFragment {
            return TrafficFragment()
        }

        fun forTraffic(trafficId: String?): TrafficFragment {
            val fragment = TrafficFragment.newInstance()
            val args = Bundle()
            args.putString(KEY_TRAFFIC_ID, trafficId)
            fragment.arguments = args

            return fragment
        }
    }

}
