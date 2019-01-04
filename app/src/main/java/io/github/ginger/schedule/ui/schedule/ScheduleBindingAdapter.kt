package io.github.ginger.schedule.ui.schedule

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import io.github.ginger.schedule.R
import io.github.ginger.schedule.util.TimeUtils
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

private val agendaTimePattern = DateTimeFormatter.ofPattern("h:mm a")

@BindingAdapter(
  value = ["agendaColor", "agendaStrokeColor", "agendaStrokeWidth"],
  requireAll = true
)
fun agendaColor(view: View, agendaColor: Int, agendaStrokeColor: Int, agendaStrokeWidth: Float) {
  view.background = (view.background as? GradientDrawable ?: GradientDrawable()).apply {
    setColor(agendaColor)
    setStroke(agendaStrokeWidth.toInt(), agendaStrokeColor)
  }
}

@BindingAdapter(value = ["agendaIcon"])
fun agendaIcon(imageView: ImageView, type: String) {
  val iconId = when (type) {
    "after_hours" -> R.drawable.ic_agenda_after_hours
    "badge" -> R.drawable.ic_agenda_badge
    "codelab" -> R.drawable.ic_agenda_codelab
    "concert" -> R.drawable.ic_agenda_concert
    "keynote" -> R.drawable.ic_agenda_keynote
    "meal" -> R.drawable.ic_agenda_meal
    "office_hours" -> R.drawable.ic_agenda_office_hours
    "sandbox" -> R.drawable.ic_agenda_sandbox
    "store" -> R.drawable.ic_agenda_store
    else -> R.drawable.ic_agenda_session
  }
  imageView.setImageDrawable(AppCompatResources.getDrawable(imageView.context, iconId))
}


@BindingAdapter(value = ["startTime", "endTime", "timeZoneId"], requireAll = true)
fun agendaDuration(
  textView: TextView,
  startTime: ZonedDateTime,
  endTime: ZonedDateTime,
  timeZoneId: ZoneId
) {
  textView.text = textView.context.getString(
    R.string.agenda_duration,
    agendaTimePattern.format(TimeUtils.zonedTime(startTime, timeZoneId)),
    agendaTimePattern.format(TimeUtils.zonedTime(endTime, timeZoneId))
  )
}
