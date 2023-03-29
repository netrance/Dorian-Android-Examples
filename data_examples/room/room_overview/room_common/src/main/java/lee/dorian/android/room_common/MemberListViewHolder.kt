package lee.dorian.android.room_common

import androidx.recyclerview.widget.RecyclerView
import lee.dorian.android.room_common.databinding.LayoutMemberItemBinding

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