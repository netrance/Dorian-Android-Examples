package lee.dorian.android.room_ex03

import lee.dorian.android.room_common.Member

interface MemberRepository {

    suspend fun insert(member: Member): Long

    suspend fun update(member: Member)

    suspend fun delete(member: Member)

    suspend fun search(name: String, point: String): List<Member>

}