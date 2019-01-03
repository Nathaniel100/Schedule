package io.github.ginger.schedule.repository

import io.github.ginger.schedule.domain.model.Block
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AgendaRepository @Inject constructor() {
  private val blocks by lazy {
    listOf(
      Block(
        title = "Breakfast",
        type = "meal",
        color = 0xff31e7b6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-08T07:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T09:30-07:00")
      ),
      Block(
        title = "Badge pick-up",
        type = "badge",
        color = 0xffe6e6e6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-08T07:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T19:00-07:00")
      ),
      Block(
        title = "Keynote",
        type = "keynote",
        color = 0xfffcd230.toInt(),
        startTime = ZonedDateTime.parse("2018-05-08T10:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T11:30-07:00")
      ),
      Block(
        title = "Lunch",
        type = "meal",
        color = 0xff31e7b6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-08T11:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T14:30-07:00")
      ),
      Block(
        title = "Codelabs",
        type = "codelab",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-08T11:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T12:30-07:00")
      ),
      Block(
        title = "Office Hours & App Review",
        type = "office_hours",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-08T11:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T12:30-07:00")
      ),
      Block(
        title = "I/O Store",
        type = "store",
        color = 0xffffffff.toInt(),
        strokeColor = 0xffff6c00.toInt(),
        startTime = ZonedDateTime.parse("2018-05-08T11:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T19:30-07:00")
      ),
      Block(
        title = "Keynote",
        type = "keynote",
        color = 0xfffcd230.toInt(),
        startTime = ZonedDateTime.parse("2018-05-08T12:45-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T13:45-07:00")
      ),
      Block(
        title = "Sessions",
        type = "session",
        color = 0xff27e5fd.toInt(),
        startTime = ZonedDateTime.parse("2018-05-08T14:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T19:00-07:00")
      ),
      Block(
        title = "Codelabs",
        type = "codelab",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-08T14:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T19:30-07:00")
      ),
      Block(
        title = "Sandbox",
        type = "sandbox",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-08T14:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T19:30-07:00")
      ),
      Block(
        title = "Office Hours & App Review",
        type = "office_hours",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-08T14:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T19:30-07:00")
      ),
      Block(
        title = "After hours party",
        type = "after_hours",
        color = 0xff202124.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-08T19:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-08T22:00-07:00")
      ),
      Block(
        title = "Breakfast",
        type = "meal",
        color = 0xff31e7b6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-09T08:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T10:00-07:00")
      ),
      Block(
        title = "Badge & device pick-up",
        type = "badge",
        color = 0xffe6e6e6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-09T08:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T19:00-07:00")
      ),
      Block(
        title = "I/O Store",
        type = "store",
        color = 0xffffffff.toInt(),
        strokeColor = 0xffff6c00.toInt(),
        startTime = ZonedDateTime.parse("2018-05-09T08:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T20:00-07:00")
      ),
      Block(
        title = "Sessions",
        type = "session",
        color = 0xff27e5fd.toInt(),
        startTime = ZonedDateTime.parse("2018-05-09T08:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T19:30-07:00")
      ),
      Block(
        title = "Codelabs",
        type = "codelab",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-09T08:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T20:00-07:00")
      ),
      Block(
        title = "Sandbox",
        type = "sandbox",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-09T08:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T20:00-07:00")
      ),
      Block(
        title = "Office Hours & App Review",
        type = "office_hours",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-09T08:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T20:00-07:00")
      ),
      Block(
        title = "Lunch",
        type = "meal",
        color = 0xff31e7b6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-09T11:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T14:30-07:00")
      ),
      Block(
        title = "Concert",
        type = "concert",
        color = 0xff202124.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-09T19:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-09T22:00-07:00")
      ),
      Block(
        title = "Breakfast",
        type = "meal",
        color = 0xff31e7b6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-10T08:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-10T10:00-07:00")
      ),
      Block(
        title = "Badge & device pick-up",
        type = "badge",
        color = 0xffe6e6e6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-10T08:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-10T16:00-07:00")
      ),
      Block(
        title = "I/O Store",
        type = "store",
        color = 0xffffffff.toInt(),
        strokeColor = 0xffff6c00.toInt(),
        startTime = ZonedDateTime.parse("2018-05-10T08:00-07:00"),
        endTime = ZonedDateTime.parse("2018-05-10T17:00-07:00")
      ),
      Block(
        title = "Sessions",
        type = "session",
        color = 0xff27e5fd.toInt(),
        startTime = ZonedDateTime.parse("2018-05-10T08:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-10T16:30-07:00")
      ),
      Block(
        title = "Codelabs",
        type = "codelab",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-10T08:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-10T16:00-07:00")
      ),
      Block(
        title = "Sandbox",
        type = "sandbox",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-10T08:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-10T16:00-07:00")
      ),
      Block(
        title = "Office Hours & App Review",
        type = "office_hours",
        color = 0xff4768fd.toInt(),
        isDark = true,
        startTime = ZonedDateTime.parse("2018-05-10T08:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-10T16:00-07:00")
      ),
      Block(
        title = "Lunch",
        type = "meal",
        color = 0xff31e7b6.toInt(),
        startTime = ZonedDateTime.parse("2018-05-10T11:30-07:00"),
        endTime = ZonedDateTime.parse("2018-05-10T14:30-07:00")
      )
    )
  }

  fun getAgenda(): List<Block> = blocks
}