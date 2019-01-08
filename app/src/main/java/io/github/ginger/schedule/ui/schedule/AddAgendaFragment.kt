package io.github.ginger.schedule.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.github.ginger.schedule.R
import io.github.ginger.schedule.databinding.FragmentAddAgendaBinding
import io.github.ginger.schedule.util.activityViewId
import io.github.ginger.schedule.util.activityViewModel
import javax.inject.Inject

class AddAgendaFragment : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var viewModel: ScheduleViewModel
  lateinit var binding: FragmentAddAgendaBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentAddAgendaBinding.inflate(inflater, container, false).apply {
      setLifecycleOwner(this@AddAgendaFragment)
    }
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = activityViewModel(viewModelFactory)
    binding.viewModel = viewModel

    activityViewId<View>(R.id.fab_add).setOnClickListener {
      viewModel.addAgenda()
    }
    viewModel.backEvent.observe(this, Observer {
      activity?.onBackPressed()
    })
  }
}