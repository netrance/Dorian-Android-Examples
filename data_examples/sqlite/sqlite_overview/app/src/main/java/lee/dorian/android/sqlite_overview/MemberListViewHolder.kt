package lee.dorian.android.sqlite_overview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import lee.dorian.android.sqlite_overview.databinding.LayoutMemberItemBinding

class MemberListViewHolder(
    val binding: LayoutMemberItemBinding
) : RecyclerView.ViewHolder(
    binding.root
) {

    fun setContent(member: Member) {
        binding.tvName.text = member.name
        binding.tvPoint.text = member.point.toString()
    }

}