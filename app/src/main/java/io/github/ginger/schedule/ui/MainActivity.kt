package io.github.ginger.schedule.ui

import android.os.Bundle
import dagger.android.DaggerActivity
import io.github.ginger.schedule.R

class MainActivity : DaggerActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


  }
}
