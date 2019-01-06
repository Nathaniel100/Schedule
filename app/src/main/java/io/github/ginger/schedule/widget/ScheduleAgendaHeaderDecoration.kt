package io.github.ginger.schedule.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.Typeface.BOLD
import android.text.Layout
import android.text.SpannableStringBuilder
import android.text.StaticLayout
import android.text.TextPaint
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.getDimensionOrThrow
import androidx.core.content.res.getDimensionPixelSizeOrThrow
import androidx.core.graphics.withTranslation
import androidx.core.text.inSpans
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.RecyclerView
import io.github.ginger.schedule.R
import io.github.ginger.schedule.domain.model.Block
import io.github.ginger.schedule.util.agendaHeaderIndexer
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class ScheduleAgendaHeaderDecoration(
  context: Context,
  blocks: List<Block>
) : RecyclerView.ItemDecoration() {

  private val dateFormatter = DateTimeFormatter.ofPattern("d")
  private val dayFormatter = DateTimeFormatter.ofPattern("eee")
  private val width: Int
  private val paddingTop: Int
  private val dayTextSize: Int
  private val textPaint: TextPaint

  init {
    val attrs =
      context.obtainStyledAttributes(R.style.Widget_IOSched_AgendaHeader, R.styleable.DateHeader)
    textPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG).apply {
      color = attrs.getColorOrThrow(R.styleable.DateHeader_android_textColor)
      textSize = attrs.getDimensionOrThrow(R.styleable.DateHeader_dateTextSize)
    }
    width = attrs.getDimensionPixelSizeOrThrow(R.styleable.DateHeader_android_width)
    paddingTop = attrs.getDimensionPixelSizeOrThrow(R.styleable.DateHeader_android_paddingTop)
    dayTextSize = attrs.getDimensionPixelSizeOrThrow(R.styleable.DateHeader_dayTextSize)

    attrs.recycle()
  }

  private val daySlots: Map<Int, StaticLayout> =
    agendaHeaderIndexer(blocks).map { it.first to createHeader(it.second) }.toMap()

  private fun createHeader(day: ZonedDateTime): StaticLayout {
    val text = SpannableStringBuilder(dateFormatter.format(day)).apply {
      append("\n")
      inSpans(AbsoluteSizeSpan(dayTextSize), StyleSpan(BOLD)) {
        append(dayFormatter.format(day).toUpperCase())
      }
    }
    return StaticLayout(text, textPaint, width, Layout.Alignment.ALIGN_CENTER, 1f, 0f, false)
  }

  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    val position = parent.getChildAdapterPosition(view)
    if (position <= 0) {
      return
    }
    if (daySlots.containsKey(position)) {
      outRect.top = paddingTop
    } else if (daySlots.containsKey(position + 1)) {
      outRect.bottom = paddingTop
    }
  }

  override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    if (daySlots.isEmpty() || parent.isEmpty()) return

    var earliestFoundHeaderPos = -1
    var prevHeaderTop = Int.MAX_VALUE

    // Loop over each attached view looking for header items.
    // Loop backwards as a lower header can push another higher one upward.
    for (i in parent.childCount - 1 downTo 0) {
      val view = parent[i]
      val viewTop = view.top + view.translationY.toInt()
      if (view.bottom > 0 && viewTop < parent.height) {
        val position = parent.getChildAdapterPosition(view)
        daySlots[position]?.let { layout ->
          textPaint.alpha = (view.alpha * 255).toInt()
          val top = (viewTop + paddingTop)
            .coerceAtLeast(paddingTop)
            .coerceAtMost(prevHeaderTop - layout.height)
          c.withTranslation(y = top.toFloat()) {
            layout.draw(c)
          }
          earliestFoundHeaderPos = position
          prevHeaderTop = viewTop - paddingTop - paddingTop
        }
      }
    }

    // If no headers found, ensure header of the first shown item is drawn.
    if (earliestFoundHeaderPos < 0) {
      earliestFoundHeaderPos = parent.getChildAdapterPosition(parent[0]) + 1
    }

    // Look back over headers to see if a prior item should be drawn sticky.
    for (headerPos in daySlots.keys.reversed()) {
      if (headerPos < earliestFoundHeaderPos) {
        daySlots[headerPos]?.let { layout ->
          val top = (prevHeaderTop - layout.height).coerceAtMost(paddingTop)
          c.withTranslation(y = top.toFloat()) {
            layout.draw(c)
          }
        }
        break
      }
    }
  }
}
