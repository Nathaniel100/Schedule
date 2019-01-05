package io.github.ginger.schedule

import io.github.ginger.schedule.matchers.isAfter
import io.github.ginger.schedule.matchers.isBefore
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Test
import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.temporal.ChronoField

class ThreeTenBpTest {

  @Test
  fun testInstant() {
    val now = Instant.now()
    val oneSecondFromNow = now.plusSeconds(1)
    assertThat(now.epochSecond, `is`(oneSecondFromNow.epochSecond - 1))

    val nowMillis = System.currentTimeMillis()
    val instant = Instant.ofEpochMilli(nowMillis)
    assertThat(instant.toEpochMilli(), `is`(nowMillis))

    val instant1 = Instant.now(Clock.system(ZoneId.of("UTC")))
    val instant2 = Instant.now(Clock.system(ZoneId.of("+08:00")))
    assertThat(instant1, `is`(instant2))
  }

  @Test
  fun testClock() {
    // Create clock
    val clockUtc = Clock.systemUTC()
    assertThat(clockUtc, notNullValue())
    val clockDefaultZone = Clock.systemDefaultZone()
    assertThat(clockDefaultZone, notNullValue())
    val zoneId = ZoneId.of("+08:00")
    val clockCustom = Clock.system(zoneId)
    assertThat(clockCustom, notNullValue())

    val nowMillis = System.currentTimeMillis()
    val instant = Instant.ofEpochMilli(nowMillis)
    val clockFixed = Clock.fixed(instant, ZoneId.of("UTC"))
    assertThat(clockFixed.millis(), `is`(nowMillis))
    val clockFixed2 = Clock.fixed(instant, ZoneId.of("+08:00"))
    assertThat(clockFixed2.millis(), `is`(nowMillis))

    val clockOffset = Clock.offset(clockFixed, Duration.ofMillis(1))
    assertThat(nowMillis, `is`(clockOffset.millis() - 1))

    // Clock to instant
    val instantFixed = clockFixed.instant()
    assertThat(instantFixed, `is`(instant))
  }

  @Test
  fun testDuration() {
    val durationSeconds = Duration.ofSeconds(5)
    assertThat(durationSeconds.seconds, `is`(5L))

    val durationSeconds2 = durationSeconds.minusSeconds(3)
    assertThat(durationSeconds2.seconds, `is`(2L))

    val durationSeconds3 = durationSeconds.plusSeconds(2)
    assertThat(durationSeconds3.seconds, `is`(7L))

    val instant1 = Instant.now()
    val instant2 = instant1.plusSeconds(1).plusMillis(5)
    val duration = Duration.between(instant1, instant2)
    assertThat(duration.seconds, `is`(1L))
    assertThat(duration.toMillis(), `is`(1005L))
  }

  @Test
  fun testLocalDate() {
    val localDate1 = LocalDate.now()
    assertThat(localDate1, `is`(LocalDate.now(ZoneId.systemDefault())))
    val localDate2 = LocalDate.now(Clock.systemUTC())
    val localDate3 = LocalDate.now(ZoneId.of("UTC"))
    assertThat(localDate2, `is`(localDate3))
    val localDate4 = LocalDate.of(2019, 1, 5)
    assertThat(localDate4.year, `is`(2019))
    assertThat(localDate4.monthValue, `is`(1))
    assertThat(localDate4.dayOfMonth, `is`(5))

    val localDate5 = localDate4.plusDays(3)
    assertThat(localDate5, isAfter(localDate4))
    val localDate6 = localDate4.minusDays(1)
    assertThat(localDate6, isBefore(localDate4))

    val zonedDateTime = ZonedDateTime.now()
    val localDate7 = LocalDate.from(zonedDateTime)
    assertThat(localDate7, notNullValue())

    val formatted = localDate4.format(DateTimeFormatter.ISO_LOCAL_DATE)
    assertThat(formatted, `is`("2019-01-05"))
  }

  @Test
  fun testLocalTime() {
    val localTime1 = LocalTime.now()
    val localTime2 = LocalTime.now(ZoneId.of("UTC"))
    val localTime3 = LocalTime.now(Clock.systemUTC())
    val localTime4 = LocalTime.of(14, 54, 0)
    val localTime5 = localTime4.plusHours(3) // 17:54:00
    assertThat(localTime5, isAfter(localTime4))
    val localTime6 = localTime4.plusHours(11) // 01:54:00
    assertThat(localTime6, isBefore(localTime4))

    val localTime7 = localTime4.minusHours(3)
    assertThat(localTime7, isBefore(localTime4))

    val formatted = localTime4.format(DateTimeFormatter.ISO_LOCAL_TIME)
    assertThat(formatted, `is`("14:54:00"))
  }

  @Test
  fun testLocalDateTime() {
    val instantNow = Instant.now()
    val dateTime = LocalDateTime.ofInstant(instantNow, ZoneId.of("+08:00"))
    val instant = dateTime.toInstant(ZoneOffset.ofHours(8))
    assertThat(instant, `is`(instantNow))
  }

  @Test
  fun testZonedDateTime() {
    val instantNow = Instant.now()
    val dateTime = ZonedDateTime.ofInstant(instantNow, ZoneId.of("+08:00"))
    val instant = dateTime.toInstant()
    assertThat(instantNow, `is`(instant))
  }

  @Test
  fun testFormatter() {
    // format
    val zonedDateTime = ZonedDateTime.parse("2019-01-04T21:14+08:00")
    val date1 = DateTimeFormatter.ISO_LOCAL_TIME.format(zonedDateTime)
    assertThat(date1, `is`("21:14:00"))
    val date2 = DateTimeFormatter.ISO_LOCAL_DATE.format(zonedDateTime)
    assertThat(date2, `is`("2019-01-04"))
    val date3 = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zonedDateTime)
    assertThat(date3, `is`("2019-01-04T21:14:00"))
    val date4 = DateTimeFormatter.BASIC_ISO_DATE.format(zonedDateTime)
    assertThat(date4, `is`("20190104+0800"))
    // YYYY-MM-DD HH:mm:dd
    val customFormatter =
      DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendValue(ChronoField.YEAR, 4)
        .appendLiteral("-")
        .appendValue(ChronoField.MONTH_OF_YEAR, 2)
        .appendLiteral("-")
        .appendValue(ChronoField.DAY_OF_MONTH, 2)
        .appendLiteral(" ")
        .appendValue(ChronoField.HOUR_OF_DAY, 2)
        .appendLiteral(":")
        .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
        .appendLiteral(":")
        .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
        .toFormatter()
    val date5 = customFormatter.format(zonedDateTime)
    assertThat(date5, `is`("2019-01-04 21:14:00"))

    // parse
    val date6 = "2019-01-04 00:01:00"
    val parsedDateTime =
      ZonedDateTime.parse(date6, customFormatter.withZone(ZoneId.systemDefault()))
    assertThat(parsedDateTime.minute, `is`(1))
  }
}