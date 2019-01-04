package io.github.ginger.schedule.util

import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime


object TimeUtils {

  fun zonedTime(time: ZonedDateTime, zoneId: ZoneId = ZoneId.systemDefault()): ZonedDateTime {
    return ZonedDateTime.ofInstant(time.toInstant(), zoneId)
  }
}