package io.github.ginger.schedule.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import io.github.ginger.schedule.databinding.FragmentScheduleBinding
import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.util.activityViewModel
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
    viewModel = activityViewModel(viewModelFactory)
    binding.viewModel = viewModel
  }
}

@BindingAdapter(value = ["agendaItems", "zoneId"])
fun setAgenda(recyclerView: RecyclerView, agendaItems: List<Block>?, zoneId: ZoneId) {
  if (recyclerView.adapter == null) {
    recyclerView.adapter = ScheduleAgendaAdapter(zoneId)
  }
  val adapter = (recyclerView.adapter as ScheduleAgendaAdapter).apply {
    this.submitList(agendaItems ?: emptyList())
    this.timeZoneId = zoneId
  }
  // TODO add ItemDecoration
}