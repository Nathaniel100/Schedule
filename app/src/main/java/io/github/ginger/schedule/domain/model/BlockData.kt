package io.github.ginger.schedule.domain.model

import android.graphics.Color
import org.threeten.bp.ZonedDateTime

object BlockData {
  val now = ZonedDateTime.now()
  val blocks = arrayOf(
    Block(
      title = "sandbox1",
      type = "meal",
      color = Color.RED,
      startTime = now,
      endTime = now.plusHours(1)
    ),
    Block(
      title = "sandbox2",
      type = "concert",
      color = Color.BLUE,
      startTime = now.plusHours(1),
      endTime = now.plusHours(2)
    ),
    Block(
      title = "sandbox3",
      type = "concert",
      color = Color.GREEN,
      startTime = now.plusHours(2),
      endTime = now.plusHours(3)
    )

  )
}