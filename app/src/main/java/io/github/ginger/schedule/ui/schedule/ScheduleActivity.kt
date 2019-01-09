package io.github.ginger.schedule.ui.schedule

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import io.github.ginger.schedule.R
import io.github.ginger.schedule.util.inTransaction
import io.github.ginger.schedule.util.showToast
import io.github.ginger.schedule.util.viewModelProvider
import kotlinx.android.synthetic.main.activity_schedule.*
import javax.inject.Inject

class ScheduleActivity : DaggerAppCompatActivity() {

  companion object {
    const val FRAGMENT_CONTAINER = R.id.fragment_container
  }

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var viewModel: ScheduleViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = viewModelProvider(viewModelFactory)
    viewModel.errorEvent.observe(this, Observer {
      it?.run { showToast(localizedMessage) }
    })
    setContentView(R.layout.activity_schedule)
    setSupportActionBar(toolbar)

    if (savedInstanceState == null) {
      supportFragmentManager.inTransaction {
        add(FRAGMENT_CONTAINER, ScheduleFragment())
      }
    }
  }

}
