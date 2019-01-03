package io.github.ginger.schedule.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.github.ginger.schedule.util.activityViewModel
import javax.inject.Inject


class ScheduleFragment : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var viewModel: ScheduleViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = activityViewModel(viewModelFactory)
  }
}