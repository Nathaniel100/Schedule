package io.github.ginger.schedule.ui.schedule

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import io.github.ginger.schedule.R
import io.github.ginger.schedule.util.inTransaction
import kotlinx.android.synthetic.main.activity_schedule.*

class ScheduleActivity : DaggerAppCompatActivity() {

  companion object {
    const val FRAGMENT_CONTAINER = R.id.fragment_container
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_schedule)
    setSupportActionBar(toolbar)

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
    }

    if (savedInstanceState == null) {
      supportFragmentManager.inTransaction {
        add(FRAGMENT_CONTAINER, ScheduleFragment(), "ScheduleFragment")
      }
    }

  }

}
