package lee.dorian.android.sqlite_overview

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable
import java.util.*

data class Member(
    val id: Long,
    val name: String,
    var point: Int
) : Serializable {

    companion object {
        const val NOT_USED_ID = -1L

        @JvmStatic
        fun createNewMember(name: String, point: Int): Member {
            return Member(NOT_USED_ID, name, point)
        }

        @JvmStatic
        fun getFrom(cursor: Cursor): List<Member> {
            val memberList = LinkedList<Member>()
            with (cursor) {
                while (moveToNext()) {
                    memberList.add(Member(
                        getLong(getColumnIndexOrThrow(BaseColumns._ID)),
                        getString(getColumnIndexOrThrow(MemberContract.MemberEntry.COLUMN_NAME_NAME)),
                        getInt(getColumnIndexOrThrow(MemberContract.MemberEntry.COLUMN_NAME_POINT))
                    ))
                }
            }

            return memberList
        }
    }

}