package bzh.naoned.naonedbusdatadisplay.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bzh.naoned.naonedbusdatadisplay.MainActivity
import bzh.naoned.naonedbusdatadisplay.R
import bzh.naoned.naonedbusdatadisplay.databinding.FragmentTrafficListBinding
import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData
import bzh.naoned.naonedbusdatadisplay.view.adapter.TrafficAdapter
import bzh.naoned.naonedbusdatadisplay.view.callback.TrafficClickCallback
import bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficListViewModel

class TrafficListFragment : Fragment() {
    private lateinit var trafficListViewModel: bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficListViewModel
    private lateinit var trafficAdapter: TrafficAdapter
    private lateinit var binding: FragmentTrafficListBinding

    private val trafficClickCallback = object: TrafficClickCallback {
        override fun onClick(trafficData: TrafficData) = (activity as MainActivity).show(trafficData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_traffic_list, container, false)

        trafficAdapter = TrafficAdapter(trafficClickCallback)
        binding.recyclerTraffic.adapter = trafficAdapter
        binding.isLoading = true

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        trafficListViewModel = ViewModelProviders.of(this).get(bzh.naoned.naonedbusdatadisplay.viewmodel.TrafficListViewModel::class.java)
        trafficListViewModel.init()

        observeViewModel(trafficListViewModel)
    }

    private fun observeViewModel(viewModel: TrafficListViewModel?) {
        // define observer to update ui when data change
        viewModel?.trafficListObservable?.observe(this, Observer { trafficData ->
            // update ui
            if (trafficData != null) {
                binding.isLoading = false
                trafficAdapter.setValues(trafficData)
            }
        })

    }

    companion object {

        fun newInstance(): TrafficListFragment {
            return TrafficListFragment()
        }
    }
}
