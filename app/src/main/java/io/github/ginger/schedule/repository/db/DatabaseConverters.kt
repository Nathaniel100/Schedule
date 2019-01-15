package io.github.ginger.schedule.repository.db

import androidx.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime


open class DatabaseConverters {
  private var zoneId = ZoneId.systemDefault()

  @TypeConverter
  fun zonedDateTimeToLong(zonedDateTime: ZonedDateTime): Long {
    return zonedDateTime.toEpochSecond()
  }

  @TypeConverter
  fun longToZonedDateTime(epochSecond: Long): ZonedDateTime {
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), zoneId)
  }
}