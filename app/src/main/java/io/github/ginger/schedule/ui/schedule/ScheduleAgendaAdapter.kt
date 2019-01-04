package io.github.ginger.schedule.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.ginger.schedule.BR
import io.github.ginger.schedule.R
import io.github.ginger.schedule.domain.model.Block
import org.threeten.bp.ZoneId

class ScheduleAgendaAdapter(var timeZoneId: ZoneId = ZoneId.systemDefault()) :
  ListAdapter<Block, AgendaViewHolder>(BlockDiff) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
    return AgendaViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        viewType,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
    holder.bind(getItem(position), timeZoneId)
  }

  override fun getItemViewType(position: Int): Int {
    return when (getItem(position).isDark) {
      true -> R.layout.item_agenda_dark
      else -> R.layout.item_agenda_light
    }
  }
}

class AgendaViewHolder(private val binding: ViewDataBinding) :
  RecyclerView.ViewHolder(binding.root) {
  fun bind(block: Block, timeZoneId: ZoneId) {
    binding.setVariable(BR.agenda, block)
    binding.setVariable(BR.timeZoneId, timeZoneId)
    binding.executePendingBindings()
  }
}

object BlockDiff : DiffUtil.ItemCallback<Block>() {

  override fun areItemsTheSame(oldItem: Block, newItem: Block): Boolean {
    return oldItem.title == newItem.title &&
        oldItem.startTime == newItem.startTime &&
        oldItem.endTime == newItem.endTime
  }

  override fun areContentsTheSame(oldItem: Block, newItem: Block): Boolean = oldItem == newItem

}