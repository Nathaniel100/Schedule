package io.github.ginger.schedule.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import io.github.ginger.schedule.R
import io.github.ginger.schedule.databinding.FragmentScheduleBinding
import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.ui.schedule.ScheduleActivity.Companion.FRAGMENT_CONTAINER
import io.github.ginger.schedule.util.activityViewId
import io.github.ginger.schedule.util.activityViewModel
import io.github.ginger.schedule.util.clearDecoration
import io.github.ginger.schedule.util.inTransaction
import io.github.ginger.schedule.widget.ScheduleAgendaHeaderDecoration
import org.threeten.bp.ZoneId
import javax.inject.Inject


class ScheduleFragment : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var viewModel: ScheduleViewModel
  lateinit var binding: FragmentScheduleBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentScheduleBinding.inflate(inflater, container, false).apply {
      setLifecycleOwner(this@ScheduleFragment)
    }
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    activityViewId<View>(R.id.fab_add).setOnClickListener {
      requireActivity().supportFragmentManager.inTransaction {
        replace(FRAGMENT_CONTAINER, AddAgendaFragment()).addToBackStack(null)
      }
    }
    viewModel = activityViewModel(viewModelFactory)
    binding.viewModel = viewModel
  }
}

@BindingAdapter(value = ["agendaItems", "zoneId"])
fun setAgenda(recyclerView: RecyclerView, agendaItems: List<Block>?, zoneId: ZoneId) {
  if (recyclerView.adapter == null) {
    recyclerView.adapter = ScheduleAgendaAdapter(zoneId)
  }
  (recyclerView.adapter as ScheduleAgendaAdapter).apply {
    this.submitList(agendaItems ?: emptyList())
    this.timeZoneId = zoneId
  }

  recyclerView.clearDecoration()
  agendaItems?.let {
    if (it.isNotEmpty()) {
      recyclerView.addItemDecoration(ScheduleAgendaHeaderDecoration(recyclerView.context, it))
    }

  }


}