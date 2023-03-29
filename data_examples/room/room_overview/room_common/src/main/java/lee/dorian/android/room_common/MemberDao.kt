package lee.dorian.android.room_common

import androidx.room.*

@Dao
interface MemberDao {

    @Query("SELECT * FROM Member")
    abstract fun selectAll(): List<Member>

    @Query("SELECT * FROM Member WHERE name LIKE :name")
    abstract fun selectByName(name: String): List<Member>

    @Query("SELECT * FROM Member WHERE point = :point")
    abstract fun selectByPoint(point: Int): List<Member>

    @Query("SELECT * FROM Member WHERE (name LIKE :name) AND (point = :point)")
    abstract fun select(name: String, point: Int): List<Member>

    @Insert
    abstract fun insertMember(member: Member): Long

    @Update
    abstract fun updateMember(member: Member)

    @Delete
    abstract fun deleteMember(member: Member)

}