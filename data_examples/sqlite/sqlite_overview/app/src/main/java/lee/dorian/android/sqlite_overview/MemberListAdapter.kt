package lee.dorian.android.sqlite_overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import lee.dorian.android.sqlite_overview.databinding.LayoutMemberItemBinding

class MemberListAdapter(
    val memberItemLongClickListener: OnMemberItemLongClickListener
) : RecyclerView.Adapter<MemberListViewHolder>() {

    var memberList: List<Member> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberListViewHolder {
        val binding = LayoutMemberItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MemberListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MemberListViewHolder, position: Int) {
        try {
            val context = viewHolder.binding.root.context

            viewHolder.setContent(memberList[position])
            viewHolder.binding.layoutMemberItem.setBackgroundColor(
                if (0 == position % 2)
                    ContextCompat.getColor(context, R.color.menu_item_background_even)
                else
                    ContextCompat.getColor(context, R.color.menu_item_background_odd)
            )

            // Set listeners.
            viewHolder.binding.layoutMemberItem.setOnLongClickListener {
                memberItemLongClickListener.onMemberItemLongClick(
                    viewHolder.binding.layoutMemberItem,
                    memberList[position]
                )
                false
            }
        }
        catch (e: ArrayIndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

}