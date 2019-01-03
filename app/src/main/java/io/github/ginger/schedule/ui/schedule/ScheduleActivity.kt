package io.github.ginger.schedule.ui.schedule

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import io.github.ginger.schedule.R
import io.github.ginger.schedule.util.viewModelProvider
import kotlinx.android.synthetic.main.activity_schedule.*
import timber.log.Timber
import javax.inject.Inject

class ScheduleActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  lateinit var viewModel: ScheduleViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = viewModelProvider(viewModelFactory)
    setContentView(R.layout.activity_schedule)
    setSupportActionBar(toolbar)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
    }

    viewModel.agenda.observe(this, Observer {
      Timber.d("Blocks size is ${it.size}")
    })
  }

}
