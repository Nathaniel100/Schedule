package io.github.ginger.schedule.matchers

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class IsAfter<T : Comparable<T>>(private val expected: T) : TypeSafeMatcher<T>() {

  override fun describeTo(description: Description?) {
    description?.appendText("isAfter $expected")
  }

  override fun matchesSafely(item: T?): Boolean {
    if (item == null) return false
    return item > expected
  }
}

class IsBefore<T : Comparable<T>>(private val expected: T) : TypeSafeMatcher<T>() {

  override fun describeTo(description: Description?) {
    description?.appendText("isAfter $expected")
  }

  override fun matchesSafely(item: T?): Boolean {
    if (item == null) return false
    return item < expected
  }
}

fun <T : Comparable<T>> isAfter(expected: T): Matcher<T> = IsAfter(expected)
fun <T : Comparable<T>> isBefore(expected: T): Matcher<T> = IsBefore(expected)