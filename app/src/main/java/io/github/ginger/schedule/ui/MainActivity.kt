package io.github.ginger.schedule.ui

import android.os.Bundle
import dagger.android.DaggerActivity
import io.github.ginger.schedule.R
import io.github.ginger.schedule.domain.model.Block
import javax.inject.Inject

class MainActivity : DaggerActivity() {

  @Inject
  lateinit var blocks: Array<Block>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


  }
}
